package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class FootballMatch {
    private int id;
    private String footballClubName1;
    private String footballClubName2;
    private double coef1;
    private double coef2;
    private double coef3;
    private String cupName;
    private String status;
    private int score1;
    private int score2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFootballClubName1() {
        return footballClubName1;
    }

    public void setFootballClubName1(String footballClubName1) {
        this.footballClubName1 = footballClubName1;
    }

    public String getFootballClubName2() {
        return footballClubName2;
    }

    public void setFootballClubName2(String footballClubName2) {
        this.footballClubName2 = footballClubName2;
    }

    public double getCoef1() {
        return coef1;
    }

    public void setCoef1(double coef1) {
        this.coef1 = coef1;
    }

    public double getCoef2() {
        return coef2;
    }

    public void setCoef2(double coef2) {
        this.coef2 = coef2;
    }

    public double getCoef3() {
        return coef3;
    }

    public void setCoef3(double coef3) {
        this.coef3 = coef3;
    }

    public String getCupName() {
        return cupName;
    }

    public void setCupName(String cupName) {
        this.cupName = cupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
