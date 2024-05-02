package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PL")
@Table(name = "ais_per_loan")
public class PersonalLoan {
    @Id
    private Long acct_no;

    @Column(precision = 5, scale = 2)
    private BigDecimal early_repay;

    public PersonalLoan() {
    }

    public PersonalLoan(BigDecimal early_repay) {
        this.early_repay = early_repay;
    }

    // Getters and Setters

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public BigDecimal getEarly_repay() {
        return early_repay;
    }

    public void setEarly_repay(BigDecimal early_repay) {
        this.early_repay = early_repay;
    }

    @Override
    public String toString() {
        return "PersonalLoan{" +
                "acct_no=" + acct_no +
                ", early_repay=" + early_repay +
                '}';
    }
}
