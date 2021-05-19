package formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("cardNumber, cv, date_4, amount")
public class DepositForm extends FormBean {
    private String cardNumber;
    private String cv;
    private String date_4;
    private String amount;
    private String action;

    public String getCardNumber() {
        return cardNumber;
    }

    @InputType("text")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCv() {
        return cv;
    }

    @InputType("text")
    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDate_4() {
        return date_4;
    }

    @InputType("text")
    public void setDate_4(String date_4) {
        this.date_4 = date_4;
    }

    public String getAmount() {
        return amount;
    }
    @InputType("text")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    @InputType("button")
    public void setAction(String s)    { action   = s;}


    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }

        if (!action.equals("submit")) {
            this.addFormError("Invalid button");
        }




        if(cv.length() != 3){
            this.addFieldError("cv","CV must contain 3 number length");
        }



        if(cardNumber.replaceAll(" ","").length() != 16){
            this.addFieldError("cardNumber","Card number must contain 16 number length");
        }


        if(date_4.length() != 4){
            this.addFieldError("date_4","Data must contain 4 number length");
        }

        if(cardNumber.chars().allMatch(Character::isLetter)){
            this.addFieldError("cardNumber","CardNumber must contain 16 number length");
        }

        try {
            Integer.parseInt(cv);
        } catch (NumberFormatException e) {
            this.addFieldError("cv" , "CV must be a decimal number");
        }

        try {
            Integer.parseInt(date_4);
        } catch (NumberFormatException e) {
            this.addFieldError("date_4" , "Date must be a decimal number");
        }


    }
}
