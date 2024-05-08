package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ais_savings")
@DiscriminatorValue("S")
public class Savings extends Account {

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    public Savings(){
    }

    public Savings(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Savings(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal interestRate) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "interestRate=" + interestRate +
                '}';
    }
}
