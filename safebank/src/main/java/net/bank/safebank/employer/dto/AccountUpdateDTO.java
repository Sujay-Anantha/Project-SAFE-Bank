package net.bank.safebank.employer.dto;

public class AccountUpdateDTO {
    private String acct_name;
    private String astreet;
    private String acity;
    private String astate;
    private Integer azipcode;
    private String astatus;
    private String acct_type;

    // Getters and Setters

    public String getAcct_name() {
        return acct_name;
    }

    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    public String getAstreet() {
        return astreet;
    }

    public void setAstreet(String astreet) {
        this.astreet = astreet;
    }

    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    public String getAstate() {
        return astate;
    }

    public void setAstate(String astate) {
        this.astate = astate;
    }

    public Integer getAzipcode() {
        return azipcode;
    }

    public void setAzipcode(Integer azipcode) {
        this.azipcode = azipcode;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public String getAcct_type() {
        return acct_type;
    }

    public void setAcct_type(String acct_type) {
        this.acct_type = acct_type;
    }
}

