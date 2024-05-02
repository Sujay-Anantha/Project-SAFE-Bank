package net.bank.safebank.employer.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ais_home_insr")
public class HomeInsurance {
    @Id
    private Integer hid;

    @Column(nullable = false, length = 30)
    private String hname;

    @Column(nullable = false, length = 30)
    private String hstreet;

    @Column(nullable = false, length = 30)
    private String hcity;

    @Column(nullable = false, length = 2)
    private String hstate;

    @Column(nullable = false)
    private int hzipcode;

    @OneToMany(mappedBy = "homeInsurance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HomeLoan> homeLoans;

    public HomeInsurance() {
    }

    public HomeInsurance(String hname, String hstreet, String hcity, String hstate, int hzipcode) {
        this.hname = hname;
        this.hstreet = hstreet;
        this.hcity = hcity;
        this.hstate = hstate;
        this.hzipcode = hzipcode;
    }

    // Getters and Setters

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHstreet() {
        return hstreet;
    }

    public void setHstreet(String hstreet) {
        this.hstreet = hstreet;
    }

    public String getHcity() {
        return hcity;
    }

    public void setHcity(String hcity) {
        this.hcity = hcity;
    }

    public String getHstate() {
        return hstate;
    }

    public void setHstate(String hstate) {
        this.hstate = hstate;
    }

    public int getHzipcode() {
        return hzipcode;
    }

    public void setHzipcode(int hzipcode) {
        this.hzipcode = hzipcode;
    }

    public List<HomeLoan> getHomeLoans() {
        return homeLoans;
    }

    public void setHomeLoans(List<HomeLoan> homeLoans) {
        this.homeLoans = homeLoans;
    }

    @Override
    public String toString() {
        return "HomeInsurance{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", hstreet='" + hstreet + '\'' +
                ", hcity='" + hcity + '\'' +
                ", hstate='" + hstate + '\'' +
                ", hzipcode=" + hzipcode +
                ", homeLoans=" + homeLoans +
                '}';
    }
}
