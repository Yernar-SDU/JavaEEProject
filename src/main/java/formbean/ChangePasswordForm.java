package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;


@FieldOrder("lastPassword, newPassword, confirmPassword")
public class ChangePasswordForm extends FormBean {
    private String lastPassword;
    private String newPassword;
    private String confirmPassword;
    private String action;

    public String getLastPassword() {
        return lastPassword;
    }

    @InputType("password")
    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    @InputType("password")
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    @InputType("password")
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAction() {
        return action;
    }

    @InputType("button")
    public void setAction(String s)    { action   = s;}


    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            System.out.println(2);
            return;
        }

        if (!action.equals("submit")) {
            System.out.println("action");
            this.addFormError("Invalid button");
        }

        if(newPassword.length() != 3){
            System.out.println("newPassword");
            this.addFieldError("newPassword","Password should contain at least 3 characters");
        }
    }
}
