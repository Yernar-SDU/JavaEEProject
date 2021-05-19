package controller;

import databean.UserCredential;
import databean.UserInfo;
import formbean.DepositForm;
import formbean.FilterCupsForm;
import model.Model;
import model.UserInfoDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;

public class ProfileAction extends Action{
    UserInfoDAO userInfoDAO;
    private FormBeanFactory<DepositForm> depositFormFormBeanFactory = new FormBeanFactory<>(DepositForm.class);

    ProfileAction(Model model){
        userInfoDAO = model.getUserInfoDAO();
    }

    @Override
    public String getName() {
        return "profile.do";
    }


    @Override
    public String performGet(HttpServletRequest request) {
        request.setAttribute("depositForm",new DepositForm());
        UserCredential userCredential = (UserCredential) request.getSession().getAttribute("user");
        try {
            UserInfo userInfo = userInfoDAO.read(userCredential.getEmail());
            request.getSession().setAttribute("userInfo",userInfo);
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "profile.jsp";
    }
}
