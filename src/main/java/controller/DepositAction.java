package controller;

import databean.Card;
import databean.UserCredential;
import databean.UserInfo;
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

public class DepositAction extends Action{
    private FormBeanFactory<ChangePasswordForm> changePasswordFormFormBeanFactory = new FormBeanFactory<>(ChangePasswordForm.class);
    private FormBeanFactory<DepositForm> depositFormFormBeanFactory = new FormBeanFactory<>(DepositForm.class);
    private CardDAO cardDAO;
    private UserInfoDAO userInfoDAO;


    DepositAction(Model model){
        cardDAO = model.getCardDAO();
        userInfoDAO = model.getUserInfoDAO();
    }
    public String getName() {
        return "deposit.do";
    }

    public String performGet(HttpServletRequest request) {
        request.setAttribute("depositForm", new DepositForm());
        request.setAttribute("passwordForm", new ChangePasswordForm());
        return "profile.jsp";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ChangePasswordForm changePasswordForm = changePasswordFormFormBeanFactory.create(request);
        DepositForm depositForm = depositFormFormBeanFactory.create(request);

        try {
            request.setAttribute("depositForm", depositForm);

            if (depositForm.hasValidationErrors()){
                return "profile.jsp";
            }

            Card card = cardDAO.read(depositForm.getCardNumber());

            if (card == null) {
                depositForm.addFieldError("curdNumber", "Card Number not found");
                return "profile.jsp";
            }
            if (card.getCV()!=(Integer.parseInt(depositForm.getCv()))) {
                depositForm.addFieldError("cv", "CV number does't match with card number");
                return "profile.jsp";
            }
            if (card.getDate_4()!=(Integer.parseInt(depositForm.getDate_4()))) {
                depositForm.addFieldError("date_4", "Date does't match with card number");
                return "profile.jsp";
            }

            if (card.getMoney()<(Integer.parseInt(depositForm.getAmount()))) {
                depositForm.addFieldError("amount", "Not enough money");
                return "profile.jsp";
            }


            UserInfo newUser =  (UserInfo) session.getAttribute("userInfo");
            newUser.setBalance(newUser.getBalance() + (Integer.parseInt(depositForm.getAmount())));
            card.setMoney(card.getMoney() - (Integer.parseInt(depositForm.getAmount())));
            cardDAO.update(card);
            userInfoDAO.update(newUser);
            session.setAttribute("userInfo", newUser);
            session.setAttribute("depositForm",new DepositForm());

        }catch (RollbackException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "deposit.do";
    }
}
