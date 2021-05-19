package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("id, amount, coef")
public class BetForm extends FormBean{
    private String coef;
    private String id;
    private String amount;
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCoef() {
        return coef;
    }

    @InputType("radio")
    public void setCoef(String coef) {
        this.coef = coef;
    }

    public String getAmount() {
        return amount;
    }

    @InputType("text")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAction()    { return action; }

    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
//        if (hasValidationErrors()) {
//            System.out.println(1);
//            return;
//        }

        if (!action.equals("submit") && !action.equals("BET")) {
            System.out.println(1);
            this.addFormError("Invalid button");
            return;
        }
        if(coef == null || amount.equals("")){
            this.addFormError("Please enter coefficient");
        }

        if(amount == null || amount.equals("")){
            this.addFormError("Please enter amount");
        }


    }
}