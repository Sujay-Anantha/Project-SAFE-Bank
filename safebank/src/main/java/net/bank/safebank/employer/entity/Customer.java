package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ais_cust")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cust_id;

    @Column(nullable = false, length = 30)
    private String cfname;

    @Column(nullable = false, length = 30)
    private String clname;

    @Column(nullable = false, length = 30)
    private String cstreet;

    @Column(nullable = false, length = 30)
    private String ccity;

    @Column(nullable = false, length = 2)
    private String cstate;

    @Column(nullable = false)
    private int czipcode;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(String cfname, String clname, String cstreet, String ccity, String cstate, int czipcode) {
        this.cfname = cfname;
        this.clname = clname;
        this.cstreet = cstreet;
        this.ccity = ccity;
        this.cstate = cstate;
        this.czipcode = czipcode;
    }

    // Getters and Setters

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCfname() {
        return cfname;
    }

    public void setCfname(String cfname) {
        this.cfname = cfname;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public String getCstreet() {
        return cstreet;
    }

    public void setCstreet(String cstreet) {
        this.cstreet = cstreet;
    }

    public String getCcity() {
        return ccity;
    }

    public void setCcity(String ccity) {
        this.ccity = ccity;
    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate;
    }

    public int getCzipcode() {
        return czipcode;
    }

    public void setCzipcode(int czipcode) {
        this.czipcode = czipcode;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cfname='" + cfname + '\'' +
                ", clname='" + clname + '\'' +
                ", cstreet='" + cstreet + '\'' +
                ", ccity='" + ccity + '\'' +
                ", cstate='" + cstate + '\'' +
                ", czipcode=" + czipcode +
                ", accounts=" + accounts +
                '}';
    }
}

