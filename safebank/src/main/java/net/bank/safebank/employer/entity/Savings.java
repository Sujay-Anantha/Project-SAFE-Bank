package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("S")
@Table(name = "ais_savings")
public class Savings {
    @Id
    private Long acct_no;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal interest_rate;

    public Savings() {
    }

    public Savings(BigDecimal interest_rate) {
        this.interest_rate = interest_rate;
    }

    // Getters and Setters

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public BigDecimal getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(BigDecimal interest_rate) {
        this.interest_rate = interest_rate;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "acct_no=" + acct_no +
                ", interest_rate=" + interest_rate +
                '}';
    }
}
