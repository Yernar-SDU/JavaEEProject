package controller;

import databean.FootballMatch;
import databean.UserBets;
import databean.UserCredential;
import model.FootballMatchDAO;
import model.Model;
import model.UserBetsDAO;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class HistoryAction extends Action{
    UserBetsDAO userBetsDAO;
    FootballMatchDAO footballMatchDAO;
    UserCredential userCredential;
    HistoryAction(Model model){
        footballMatchDAO = model.getFootballMatchDAO();
        userBetsDAO = model.getUserBetsDAO();
    }
    @Override
    public String getName() {
        return "history.do";
    }

    @Override
    public String performGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        userCredential = (UserCredential) request.getSession().getAttribute("user");
        if(userCredential == null){
            return "login.do";
        }
        request.setAttribute("userBetsDAO",userBetsDAO);
        request.setAttribute("footballMatchDAO",footballMatchDAO);
        UserBets[] userBets;
        try{
            userBets = userBetsDAO.getItems(userCredential.getEmail());
            session.setAttribute("histories",userBets);
        }catch (RollbackException ex){}

        return "history.jsp";
    }
}
