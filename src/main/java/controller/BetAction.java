package controller;

import databean.FootballMatch;
import databean.UserBets;
import databean.UserCredential;
import databean.UserInfo;
import formbean.BetForm;
import formbean.FilterCupsForm;
import model.FootballMatchDAO;
import model.Model;
import model.UserBetsDAO;
import model.UserInfoDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

public class BetAction extends Action{
    private FormBeanFactory<BetForm> betFormFormBeanFactory = new FormBeanFactory<>(BetForm.class);
    UserBetsDAO userBetsDAO;
    FootballMatchDAO footballMatchDAO;
    UserInfoDAO userInfoDAO;
    private FormBeanFactory<FilterCupsForm> filterCupsFormFormBeanFactory = new FormBeanFactory<>(FilterCupsForm.class);

    BetAction(Model model){
        userBetsDAO = model.getUserBetsDAO();
        footballMatchDAO = model.getFootballMatchDAO();
        userInfoDAO = model.getUserInfoDAO();
    }

    @Override
    public String getName() {
        return "bet.do";
    }


    @Override
    public String performGet(HttpServletRequest request) {
        BetForm betForm =  betFormFormBeanFactory.create(request);
        request.getSession().setAttribute("betForm",betForm);
        try {


            FootballMatch footballMatch = footballMatchDAO.read(Integer.parseInt(betForm.getId()));
            if(betForm.hasValidationErrors()){
                request.setAttribute("whichMatchId",footballMatch.getId());
                return "main.jsp";
            }
            UserCredential userCredential = (UserCredential) request.getSession().getAttribute("user");
            if(userCredential == null){
                request.setAttribute("whichMatchId",footballMatch.getId());
                betForm.addFormError("Please login to account");
                return "main.jsp";
            }
            UserBets userBets = new UserBets();
            userBets.setEmail(userCredential.getEmail());
            userBets.setMatch_id(Integer.parseInt(betForm.getId()));
            userBets.setAmount(Integer.parseInt(betForm.getAmount()));
            userBets.setCoef_position(Integer.parseInt(betForm.getCoef()));
            String date = Calendar.getInstance().getTime().toString();
            userBets.setDate(date);

            if(footballMatchDAO.read(Integer.parseInt(betForm.getId())).getStatus().equals("Done")){
                request.setAttribute("whichMatchId",footballMatch.getId());
                betForm.addFormError("You can't bet to finished matches");
                return "main.jsp";
            }
            UserInfo userInfo = userInfoDAO.read(userCredential.getEmail());
            if(userInfo.getBalance() < Integer.parseInt(betForm.getAmount())){
                request.setAttribute("whichMatchId",footballMatch.getId());
                betForm.addFormError("You haven't enough balance");
                return "main.jsp";
            }
            request.setAttribute("whichMatchId",footballMatch.getId());
            userBetsDAO.create(userBets);
            userInfo.setBalance(userInfo.getBalance() - Integer.parseInt(betForm.getAmount()));
            userInfoDAO.update(userInfo);
            request.getSession().setAttribute("userInfo",userInfo);
            return "main.jsp";
        } catch (RollbackException e) {
            e.printStackTrace();
        }


        return "main.jsp";
    }

}
