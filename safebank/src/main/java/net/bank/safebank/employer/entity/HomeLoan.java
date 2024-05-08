package net.bank.safebank.employer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ais_home_loan")
@DiscriminatorValue("HL")
public class HomeLoan extends Loan {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hid")
    private HomeInsurance homeInsurance;

    @Column(name = "hbuilt_year", nullable = false)
    private Short houseBuiltYear;

    @Column(name = "yr_premium", nullable = false)
    private BigDecimal yearlyPremium;

    public HomeLoan(){
        super();
    }

    public HomeLoan(BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, HomeInsurance homeInsurance, Short houseBuiltYear, BigDecimal yearlyPremium) {
        super(loanRate, loanAmount, loanPayment, loanMonths);
        this.homeInsurance = homeInsurance;
        this.houseBuiltYear = houseBuiltYear;
        this.yearlyPremium = yearlyPremium;
    }

    public HomeLoan(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, HomeInsurance homeInsurance, Short houseBuiltYear, BigDecimal yearlyPremium) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer, loanRate, loanAmount, loanPayment, loanMonths);
        this.homeInsurance = homeInsurance;
        this.houseBuiltYear = houseBuiltYear;
        this.yearlyPremium = yearlyPremium;
    }

    public HomeInsurance getHomeInsurance() {
        return homeInsurance;
    }

    public void setHomeInsurance(HomeInsurance homeInsurance) {
        this.homeInsurance = homeInsurance;
    }

    public Short getHouseBuiltYear() {
        return houseBuiltYear;
    }

    public void setHouseBuiltYear(Short houseBuiltYear) {
        this.houseBuiltYear = houseBuiltYear;
    }

    public BigDecimal getYearlyPremium() {
        return yearlyPremium;
    }

    public void setYearlyPremium(BigDecimal yearlyPremium) {
        this.yearlyPremium = yearlyPremium;
    }

    @Override
    public String toString() {
        return "HomeLoan{" +
                "homeInsurance=" + homeInsurance +
                ", houseBuiltYear=" + houseBuiltYear +
                ", yearlyPremium=" + yearlyPremium +
                '}';
    }
}