package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.UserCredential;
import databean.UserInfo;
import formbean.CreateAccountForm;
import model.Model;
import model.UserCredentialDAO;
import model.UserInfoDAO;




public class CreateAccountAction extends Action {
    private FormBeanFactory<CreateAccountForm> formBeanFactory = new FormBeanFactory<>(CreateAccountForm.class);
    private UserInfoDAO userInfoDAO;
    private UserCredentialDAO userCredentialDAO;

    public CreateAccountAction(Model model) {
    	userInfoDAO = model.getUserInfoDAO();
    	userCredentialDAO = model.getUserCredentialDAO();
    }

    public String getName() {
        return "createaccountaction.do";
    }

    public String performGet(HttpServletRequest request) {
    	
        request.setAttribute("form", new CreateAccountForm());
        return "register.jsp";
    }

    public String performPost(HttpServletRequest request) {
        CreateAccountForm form = formBeanFactory.create(request);
        try {
            request.setAttribute("form", form);
            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "register.jsp";
            }
//            if (form.getEmail()=="") {
//            	form.addFieldError("email", "Required field");
//                return "register.jsp";
//            }
//            if (form.getName()=="") {
//            	form.addFieldError("name", "Required field");
//                return "register.jsp";
//            }
//            if (form.getSurname()=="") {
//            	form.addFieldError("surname", "Required field");
//                return "register.jsp";
//            }
//            if (form.getPassword()=="") {
//            	form.addFieldError("password", "Required field");
//                return "register.jsp";
//            }
//            if (!form.getEmail().contains("@mail.ru")) {
//            	form.addFieldError("email", "Email is invalid");
//                return "register.jsp";
//            }
            UserInfo info = userInfoDAO.read(form.getEmail());
            // Look up the user

            if (info != null) {
                form.addFieldError("email", "Email is already registered");
                return "register.jsp";
            }

            UserInfo user = new UserInfo();
            UserCredential userCredential = new UserCredential();
            
            user.setEmail(form.getEmail());
            user.setName(form.getName());
            user.setSurname(form.getSurname());
            user.setBalance(0);
            
            userCredential.setEmail(form.getEmail());
            userCredential.setPassword(form.getPassword());
            
            userInfoDAO.create(user);
            userCredentialDAO.create(userCredential);
            
            request.setAttribute("form", new CreateAccountForm());
            return "login.do";

        } catch (RollbackException e) {
        	e.printStackTrace();
        }
        request.setAttribute("form", new CreateAccountForm());
        return "login.do";
    }
}

