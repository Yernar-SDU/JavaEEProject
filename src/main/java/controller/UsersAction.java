package controller;

import databean.UserInfo;
import model.Model;
import model.UserInfoDAO;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;

public class UsersAction extends Action{
    UserInfoDAO userInfoDAO;

    UsersAction(Model model){
        userInfoDAO = model.getUserInfoDAO();
    }

    @Override
    public String getName() {
        return "users.do";
    }

    @Override
    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("users", userInfoDAO.match());
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "users.jsp";
    }

    @Override
    public String performPost(HttpServletRequest request) {
        return "users.jsp";
    }
}
