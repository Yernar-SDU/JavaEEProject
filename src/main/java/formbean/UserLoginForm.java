package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("email, password")
public class UserLoginForm extends FormBean{
    private String email;
    private String password;
    private String action;

    public String getEmail()  { return email; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }

    @InputType("text")
    public void setEmail(String s)  { email = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }

    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
        	System.out.println(3);
            return;
        }
        if(!action.equals("submit")){
        	System.out.println(3);
            this.addFormError("Invalid button");
        }
        
    }
}