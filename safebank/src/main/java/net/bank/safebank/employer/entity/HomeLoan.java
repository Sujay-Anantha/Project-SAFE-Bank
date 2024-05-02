package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("HL")
@Table(name = "ais_home_loan")
public class HomeLoan {
    @Id
    private Long acct_no;

    @Column(nullable = false)
    private Integer hacct_no;

    @Column(nullable = false)
    private Short hbuilt_year;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal yr_premium;

    @ManyToOne
    @JoinColumn(name = "hid", nullable = false)
    private HomeInsurance homeInsurance;

    public HomeLoan() {
    }

    public HomeLoan(Short hbuilt_year, BigDecimal yr_premium) {
        this.hbuilt_year = hbuilt_year;
        this.yr_premium = yr_premium;
    }

    // Getters and Setters

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public Integer getHacct_no() {
        return hacct_no;
    }

    public void setHacct_no(Integer hacct_no) {
        this.hacct_no = hacct_no;
    }

    public Short getHbuilt_year() {
        return hbuilt_year;
    }

    public void setHbuilt_year(Short hbuilt_year) {
        this.hbuilt_year = hbuilt_year;
    }

    public BigDecimal getYr_premium() {
        return yr_premium;
    }

    public void setYr_premium(BigDecimal yr_premium) {
        this.yr_premium = yr_premium;
    }

    public HomeInsurance getHomeInsurance() {
        return homeInsurance;
    }

    public void setHomeInsurance(HomeInsurance homeInsurance) {
        this.homeInsurance = homeInsurance;
    }

    @Override
    public String toString() {
        return "HomeLoan{" +
                "acct_no=" + acct_no +
                ", hacct_no=" + hacct_no +
                ", hbuilt_year=" + hbuilt_year +
                ", yr_premium=" + yr_premium +
                ", homeInsurance=" + homeInsurance +
                '}';
    }
}
