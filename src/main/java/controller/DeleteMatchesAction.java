package controller;

import databean.UserBets;
import formbean.IdForm;
import model.FootballMatchDAO;
import model.Model;
import model.UserBetsDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;

public class DeleteMatchesAction extends Action{
    FormBeanFactory<IdForm> formBean = new FormBeanFactory<>(IdForm.class);
    FootballMatchDAO footballMatchDAO;
    UserBetsDAO userBetsDAO;

    DeleteMatchesAction(Model model){
        userBetsDAO = model.getUserBetsDAO();
        footballMatchDAO = model.getFootballMatchDAO();
    }

    @Override
    public String getName() {
        return "deleteMatch.do";
    }

    @Override
    public String performPost(HttpServletRequest request) {
        IdForm form = formBean.create(request);

        try{
            UserBets[] userBets = userBetsDAO.match(MatchArg.equals("match_id", form.getIdAsInt()));

            for (UserBets i: userBets) {
                userBetsDAO.delete(i);
            }
            footballMatchDAO.delete(form.getIdAsInt());
            return "matches.do";
        } catch (RollbackException e) {
            e.printStackTrace();
        }

        return "matches.do";
    }
}
