package controller;

import databean.FootballMatch;
import databean.UserCredential;
import databean.UserInfo;
import formbean.BetForm;
import formbean.FilterCupsForm;
import formbean.FootballMatchForm;
import model.FootballMatchDAO;
import model.Model;
import model.UserInfoDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class MainAction extends Action {
    FootballMatchDAO footballMatchDAO;
    UserCredential userCredential;
    UserInfo userInfo;
    UserInfoDAO userInfoDAO;
    private FormBeanFactory<FootballMatchForm> footballMatchFormFormBeanFactory = new FormBeanFactory<>(FootballMatchForm.class);
    private FormBeanFactory<FilterCupsForm> filterCupsFormFormBeanFactory = new FormBeanFactory<>(FilterCupsForm.class);

    public MainAction(Model model) {
        userInfoDAO = model.getUserInfoDAO();
        footballMatchDAO = model.getFootballMatchDAO();
    }

    public String getName() {
        return "main.do";
    }

    @Override
    public String performGet(HttpServletRequest request) {
        FootballMatchForm footballMatchForm = footballMatchFormFormBeanFactory.create(request);
        request.setAttribute("footballMatchForm",footballMatchForm);
        userCredential = (UserCredential) request.getSession().getAttribute("user");
        try {
            if(userCredential != null){
                System.out.println(1);
                userInfo = userInfoDAO.read(userCredential.getEmail());
                request.getSession().setAttribute("userInfo",userInfo);
            }
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("betForm",new BetForm());
        request.setAttribute("form1",new FilterCupsForm());
        request.setAttribute("footballMatchDAO",footballMatchDAO);
        HttpSession session = request.getSession();
        String status = (String) session.getAttribute("status");
        String cup = (String) session.getAttribute("cup");
        System.out.println(cup);
        if(status == null){
            status = "Live";
        }
        try {
            FootballMatch[] footballMatches;
            if(cup == null){
                footballMatches = footballMatchDAO.getItems(status);
            }else {
                footballMatches = footballMatchDAO.getItems(cup,status);
            }

            session.setAttribute("footballMatches",footballMatches);
        } catch (RollbackException e) {
            e.printStackTrace();
        }


        return "main.jsp";
    }


    @Override
    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        FilterCupsForm filterCupsForm = filterCupsFormFormBeanFactory.create(request);
        String status;

        if(filterCupsForm.getAction().equals("Live")){
            session.setAttribute("status","Live");
            session.setAttribute("cup",null);
            return performGet(request);
        }else if (filterCupsForm.getAction().equals("Line")){
            session.setAttribute("status","Line");
            session.setAttribute("cup",null);
            return performGet(request);
        }
        session.setAttribute("cup",filterCupsForm.getAction());
        status = (String) session.getAttribute("status");
        return performGet(request);

    }


}
