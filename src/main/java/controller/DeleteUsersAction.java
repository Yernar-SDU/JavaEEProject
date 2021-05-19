package controller;

import databean.UserBets;
import formbean.IdForm;
import model.*;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUsersAction extends Action{
    FormBeanFactory<IdForm> formBean = new FormBeanFactory<>(IdForm.class);
    UserInfoDAO userInfoDAO;
    UserCredentialDAO userCredentialDAO;
    UserBetsDAO userBetsDAO;

    DeleteUsersAction(Model model){
        userInfoDAO = model.getUserInfoDAO();
        userCredentialDAO = model.getUserCredentialDAO();
        userBetsDAO = model.getUserBetsDAO();
    }

    @Override
    public String getName() {
        return "deleteUsers.do";
    }

    @Override
    public String performPost(HttpServletRequest request) {
        IdForm form = formBean.create(request);

        try {
            userInfoDAO.delete(form.getId());
            userCredentialDAO.delete(form.getId());
            UserBets[] userBets = userBetsDAO.match(MatchArg.equals("email", form.getIdAsInt()));

            for (UserBets i: userBets) {
                userBetsDAO.delete(i);
            }
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "users.do";
    }
}
