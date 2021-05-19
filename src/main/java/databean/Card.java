package databean;


import org.genericdao.PrimaryKey;

@PrimaryKey("cardNumber")
public class Card {

    private String cardNumber;
    private int CV;
    private int date_4;
    private int money;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCV() {
        return CV;
    }

    public void setCV(int CV) {
        this.CV = CV;
    }

    public int getDate_4() {
        return date_4;
    }

    public void setDate_4(int date_4) {
        this.date_4 = date_4;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
