package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;


@FieldOrder("footballClubName1, footballClubName2, coef1, coef2, coef3, cupName, status, action")
public class NewMatchForm extends FormBean{
    private String footballClubName1;
    private String footballClubName2;
    private String coef1;
    private String coef2;
    private String coef3;
    private String cupName;
    private String status;
    private String action;


    public String getFootballClubName1() {
        return footballClubName1;
    }

    @InputType("text")
    public void setFootballClubName1(String footballClubName1) {
        this.footballClubName1 = footballClubName1;
    }

    public String getFootballClubName2() {
        return footballClubName2;
    }

    @InputType("text")
    public void setFootballClubName2(String footballClubName2) {
        this.footballClubName2 = footballClubName2;
    }

    public String getCoef1() {
        return coef1;
    }

    @InputType("text")
    public void setCoef1(String coef1) {
        this.coef1 = coef1;
    }

    public String getCoef2() {
        return coef2;
    }

    @InputType("text")
    public void setCoef2(String coef2) {
        this.coef2 = coef2;
    }

    public String getCoef3() {
        return coef3;
    }

    @InputType("text")
    public void setCoef3(String coef3) {
        this.coef3 = coef3;
    }

    public String getCupName() {
        return cupName;
    }

    @InputType("text")
    public void setCupName(String cupName) {
        this.cupName = cupName;
    }

    public String getStatus() {
        return status;
    }

    @InputType("text")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    @InputType("button")
    public void setAction(String action) {
        this.action = action;
    }

    public void validate(){
        super.validate();

        if(hasValidationErrors()){
            return;
        }

        if(!action.equals("submit") && !action.equals("logout")){
            this.addFormError("Invalid button");
        }

        try {
            Double.parseDouble(coef1);
        } catch (NumberFormatException e) {
            this.addFieldError("coef1" , "Coefficient should be a float number");
        }

        try {
            Double.parseDouble(coef2);
        } catch (NumberFormatException e) {
            this.addFieldError("coef2" , "Coefficient should be a float number");
        }

        try {
            Double.parseDouble(coef3);
        } catch (NumberFormatException e) {
            this.addFieldError("coef3" , "Coefficient should be a float number");
        }


        if(coef1.length() > 3 || coef2.length() > 3 || coef3.length() > 3){
            this.addFormError("Coefficient should be maximum 3 digit float.");
        }


    }
}
