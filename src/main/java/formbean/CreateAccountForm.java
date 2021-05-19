package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;


@FieldOrder("email, name, surname, password")
public class CreateAccountForm extends FormBean {

    private String email;
    private String name;
    private String surname;
    private String password;
    private String action;


    public String getEmail() {return email;}

    @InputType("text")
    public void setEmail(String email){this.email = email;}

    public String getName(){return name;}

    @InputType("text")
    public void setName(String name) {this.name = name;}
    
    public String getSurname(){return surname;}

    @InputType("text")
    public void setSurname(String name) {this.surname = name;}

    public String getPassword() { return password; }

    @InputType("password")
    public void setPassword(String password) { this.password = password.trim();}

    public String getAction() { return action; }

    @InputType("button")
    public void setAction(String action) { this.action = action; }

    public void validate(){
        super.validate();
        if(hasValidationErrors()){
            return;
        }
        if(!action.equals("submit") && !action.equals("logout")){
            this.addFormError("Invalid button");
        }

        if (email.matches(".*[<>\"].*")) {
            this.addFieldError("email", "May not contain angle brackets or quotes");
        }
        
        if (name.matches(".*[0-9].*")) {
            this.addFieldError("name", "May not contain from integers");
        }
        
        if (surname.matches(".*[0-9].*")) {
            this.addFieldError("surname", "May not contain from integers");
        }

        if(password.length() < 3){
            this.addFieldError("password", "Invalid password length. It should be at least 3");
        }
    }
}
