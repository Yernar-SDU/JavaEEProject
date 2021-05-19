package formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

public class FilterCupsForm extends FormBean {
    private String action;

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


    }
}
