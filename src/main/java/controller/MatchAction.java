package controller;

import formbean.FilterCupsForm;
import model.FootballMatchDAO;
import model.Model;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;

public class MatchAction extends Action{
    FootballMatchDAO footballMatchDAO;

    MatchAction(Model model){
        footballMatchDAO = model.getFootballMatchDAO();
    }

    @Override
    public String getName() {
        return "matches.do";
    }

    @Override
    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("matches", footballMatchDAO.match());
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "matches.jsp";
    }

    @Override
    public String performPost(HttpServletRequest request) {
        return "matches.jsp";
    }
}
