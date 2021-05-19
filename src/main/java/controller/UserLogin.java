package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.UserInfo;
import model.UserInfoDAO;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import formbean.UserLoginForm;
import model.AdminCredentialDAO;
import model.Model;
import model.UserCredentialDAO;
import databean.AdminCredential;
import databean.UserCredential;





public class UserLogin extends Action {
    private FormBeanFactory<UserLoginForm> formBeanFactory = new FormBeanFactory<>(UserLoginForm.class);

    private UserCredentialDAO userCredentialDAO;
    UserInfoDAO userInfoDAO;
    
    public UserLogin(Model model) {
    	userInfoDAO = model.getUserInfoDAO();;
        userCredentialDAO = model.getUserCredentialDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String performGet(HttpServletRequest request) {
        request.setAttribute("form", new UserLoginForm());
        return "userlogin.jsp";
    }

    public String performPost(HttpServletRequest request) {
    	
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        UserLoginForm form = formBeanFactory.create(request);
        

        try {
            request.setAttribute("form", form);
            

//             Any validation errors?
            if (form.hasValidationErrors()) {
            	
                return "userlogin.jsp";
            }

            // Look up the user
            UserCredential user = userCredentialDAO.read(form.getEmail());
            
          
            if (user == null) {
                form.addFieldError("email", "User Email not found");
                return "userlogin.jsp";
            }
            

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "userlogin.jsp";
            }
            if (user.getEmail().equals("admin")) {
                return "create.do";
            }
            

            // Attach (this copy of) the user bean to the session

            UserInfo userInfo = userInfoDAO.read(user.getEmail());
            session.setAttribute("user", user);
            session.setAttribute("userInfo", userInfo);

            // If redirectTo is null, redirect to the "todolist" action

            return "main.do";
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return "main.do";
    }
}