package controller;

import databean.UserCredential;
import formbean.ChangePasswordForm;
import formbean.DepositForm;
import model.CardDAO;
import model.Model;
import model.UserCredentialDAO;
import model.UserInfoDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordAction extends Action{
    private FormBeanFactory<ChangePasswordForm> changePasswordFormFormBeanFactory = new FormBeanFactory<>(ChangePasswordForm.class);
    private UserCredentialDAO userCredentialDAO;
    private FormBeanFactory<DepositForm> depositFormFormBeanFactory = new FormBeanFactory<>(DepositForm.class);

    ChangePasswordAction(Model model){
        userCredentialDAO = model.getUserCredentialDAO();
    }

    public String getName() {
        return "changePassword.do";
    }

    public String performGet(HttpServletRequest request) {
        request.setAttribute("passwordForm", new ChangePasswordForm());
        request.setAttribute("depositForm", new DepositForm());
        return "profile.jsp";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("11111111111111");
        ChangePasswordForm passwordForm = changePasswordFormFormBeanFactory.create(request);

        try {
            request.setAttribute("passwordForm", passwordForm);

            if (passwordForm.hasValidationErrors()){
                return "profile.jsp";
            }

            System.out.println("222222222");
            UserCredential user =  (UserCredential) session.getAttribute("user");

            if(user == null){
                passwordForm.addFormError("This user doesn't exists");
                return "profile.jsp";
            }
            if (!user.getPassword().equals(passwordForm.getLastPassword())) {
                passwordForm.addFieldError("lastPassword", "Incorrect Password");
                return "profile.jsp";
            }

            System.out.println("333333333");
            if (!passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword())) {
                passwordForm.addFieldError("confirmPassword", "Passwords are not same");
                return "profile.jsp";
            }

            System.out.println("444444444");
            user.setPassword(passwordForm.getNewPassword());
            userCredentialDAO.update(user);

            System.out.println("55555555555");
            session.setAttribute("userCredential", user);
            session.setAttribute("passwordForm", new ChangePasswordForm());

            System.out.println("zhete ma?>>>>>>>>>>>>>>>>>>>>>>");
        }catch (RollbackException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "changePassword.do";
    }
}
