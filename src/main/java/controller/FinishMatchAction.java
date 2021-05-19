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
import java.util.ArrayList;

public class FinishMatchAction extends Action{
    private FormBeanFactory<FilterCupsForm> filterCupsFormFormBeanFactory = new FormBeanFactory<>(FilterCupsForm.class);
    private FormBeanFactory<BetForm> betFormFormBeanFactory = new FormBeanFactory<>(BetForm.class);
    private FootballMatchDAO footballMatchDAO;
    private UserInfoDAO userInfoDAO;
    private UserCredential userCredential;
    private UserBetsDAO userBetsDAO;
    private BetForm betForm;
    FinishMatchAction(Model model){
        userBetsDAO = model.getUserBetsDAO();
        footballMatchDAO = model.getFootballMatchDAO();
        userInfoDAO = model.getUserInfoDAO();
    }

    @Override
    public String getName() {
        return "finishMatch.do";
    }


    @Override
    public String performGet(HttpServletRequest request) {
        BetForm betForm = betFormFormBeanFactory.create(request);
        FilterCupsForm filterCupsForm = filterCupsFormFormBeanFactory.create(request);
        FootballMatch[] footballMatches = (FootballMatch[]) request.getSession().getAttribute("footballMatches");
        if(request.getSession().getAttribute("user") == null){
            betForm.addFormError("Please log in");
            return "main.jsp";
        }
        try {
            FootballMatch footballMatch = footballMatchDAO.read(Integer.parseInt(filterCupsForm.getAction()));
            if(footballMatch.getStatus().equals("Done")){
                betForm.addFormError("This match has already finished");
                return "main.jsp";
            }
            //random
            double rand1 = Math.random() * 10 / 2;
            double rand2 = Math.random() * 10 / 2;
            int score1 = (int)Math.floor(rand1);
            int score2 = (int)Math.floor(rand2);
            footballMatch.setScore1(score1);
            footballMatch.setScore2(score2);
            footballMatch.setStatus("Done");
            footballMatchDAO.update(footballMatch);
            UserBets[] userBets = userBetsDAO.getItems();

            //Balance update

            double cef;
            for (int i = 0; i < userBetsDAO.getItems().length; i++) {
                if(userBets[i].getMatch_id() == footballMatch.getId()){
                    UserInfo userInfo = userInfoDAO.read(userBets[i].getEmail());
                    if((footballMatch.getScore2() > footballMatch.getScore1() && userBets[i].getCoef_position() == 3) ||
                            (footballMatch.getScore1() > footballMatch.getScore2() && userBets[i].getCoef_position() == 1) ||
                            (footballMatch.getScore2() == footballMatch.getScore1() && userBets[i].getCoef_position() == 2)){
                        if(userBets[i].getCoef_position()==1){cef = footballMatch.getCoef1();}
                        else if(userBets[i].getCoef_position()==2){cef = footballMatch.getCoef2();}
                        else{cef = footballMatch.getCoef3();}
                        System.out.println(userInfo.getBalance());
                        System.out.println(userBets[i].getAmount());
                        System.out.println(cef);
                        userInfo.setBalance((int) (userInfo.getBalance() + userBets[i].getAmount() * cef));
                        userInfoDAO.update(userInfo);
                        request.getSession().setAttribute("userInfo",userInfo);
                    }

                }
            }
            for (int i = 0; i < footballMatches.length; i++) {
                if(footballMatches[i].getId() == Integer.parseInt(filterCupsForm.getAction())){

                    footballMatches[i].setStatus("Done");
                    footballMatches[i].setScore1(score1);
                    footballMatches[i].setScore2(score2);

                }
            }
            request.getSession().setAttribute("footballMatches",footballMatches);

        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "main.jsp";
    }
}
