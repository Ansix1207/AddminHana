package hana.teamfour.addminhana.entity;

import java.sql.Date;

public class AccountInfo {
    private String acc_type;
    private String acc_pname;
    private Date acc_maturitydate;
    private Double acc_interestrate;
    private Integer acc_balance;

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }
    public String getAcc_pname() {
        return acc_pname;
    }

    public void setAcc_pname(String acc_pname) {
        this.acc_pname = acc_pname;
    }

    public Date getAcc_maturitydate() {
        return acc_maturitydate;
    }

    public void setAcc_maturitydate(Date acc_maturitydate) {
        this.acc_maturitydate = acc_maturitydate;
    }

    public Double getAcc_interestrate() {
        return acc_interestrate;
    }

    public void setAcc_interestrate(Double acc_interestrate) {
        this.acc_interestrate = acc_interestrate;
    }

    public Integer getAcc_balance() {
        return acc_balance;
    }

    public void setAcc_balance(Integer acc_balance) {
        this.acc_balance = acc_balance;
    }
}