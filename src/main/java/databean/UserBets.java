package databean;


import org.genericdao.PrimaryKey;
@PrimaryKey("id")
public class UserBets {
    private int id;
    private String email;
    private int match_id;
    private int amount;
    private int coef_position;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCoef_position() {
        return coef_position;
    }

    public void setCoef_position(int coef_position) {
        this.coef_position = coef_position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
