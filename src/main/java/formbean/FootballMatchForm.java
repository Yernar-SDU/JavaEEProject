package formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("coef1, coef2, coef3, amount, action")
public class FootballMatchForm extends FormBean {

    private String coef1;
    private String coef2;
    private String coef3;
    private String amount;
    private String action;


    public String getCoef1() {
        return coef1;
    }

    @InputType("radio")
    public void setCoef1(String coef1) {
        this.coef1 = coef1;
    }

    public String getCoef2() {
        return coef2;
    }

    @InputType("radio")
    public void setCoef2(String coef2) {
        this.coef2 = coef2;
    }

    public String getCoef3() {
        return coef3;
    }

    @InputType("radio")
    public void setCoef3(String coef3) {
        this.coef3 = coef3;
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
    public void setAction(String action) {
        this.action = action;
    }
}
