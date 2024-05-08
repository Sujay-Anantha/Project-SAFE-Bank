package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ais_per_loan")
@DiscriminatorValue("PL")
public class PersonalLoan extends Loan {

    @Column(name = "early_repay", nullable = true)
    private BigDecimal earlyRepaymentFee;

    public PersonalLoan(){
        super();
    }

    public PersonalLoan(BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, BigDecimal earlyRepaymentFee) {
        super(loanRate, loanAmount, loanPayment, loanMonths);
        this.earlyRepaymentFee = earlyRepaymentFee;
    }

    public PersonalLoan(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, BigDecimal earlyRepaymentFee) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer, loanRate, loanAmount, loanPayment, loanMonths);
        this.earlyRepaymentFee = earlyRepaymentFee;
    }

    public BigDecimal getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }

    public void setEarlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
    }

    @Override
    public String toString() {
        return "PersonalLoan{" +
                "earlyRepaymentFee=" + earlyRepaymentFee +
                '}';
    }
}
