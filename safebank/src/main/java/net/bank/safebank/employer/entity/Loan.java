package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HomeLoan.class, name = "HL"),
        @JsonSubTypes.Type(value = StudentLoan.class, name = "SL"),
        @JsonSubTypes.Type(value = PersonalLoan.class, name = "PL")
})
@Entity
@Table(name = "ais_loan")
@DiscriminatorValue("L")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ltype", discriminatorType = DiscriminatorType.STRING)
public class Loan extends Account {

    @Column(name = "lrate", nullable = false)
    private BigDecimal loanRate;

    @Column(name = "lamount", nullable = false)
    private BigDecimal loanAmount;

    @Column(name = "lpayment", nullable = false)
    private BigDecimal loanPayment;

    @Column(name = "lmonths", nullable = false)
    private Short loanMonths;

    public  Loan(){
    }

    public Loan(BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths) {
        this.loanRate = loanRate;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanMonths = loanMonths;
    }

    public Loan(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer);
        this.loanRate = loanRate;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanMonths = loanMonths;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getLoanPayment() {
        return loanPayment;
    }

    public void setLoanPayment(BigDecimal loanPayment) {
        this.loanPayment = loanPayment;
    }

    public Short getLoanMonths() {
        return loanMonths;
    }

    public void setLoanMonths(Short loanMonths) {
        this.loanMonths = loanMonths;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanRate=" + loanRate +
                ", loanAmount=" + loanAmount +
                ", loanPayment=" + loanPayment +
                ", loanMonths=" + loanMonths +
                '}';
    }
}
