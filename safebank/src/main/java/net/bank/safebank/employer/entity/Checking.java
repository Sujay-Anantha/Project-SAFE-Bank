package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ais_checking")
@DiscriminatorValue("C")
public class Checking extends Account {

    @Column(name = "service_charge", nullable = false)
    private BigDecimal serviceCharge;

    public  Checking(){
    }

    public Checking(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Checking(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal serviceCharge) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer);
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    public String toString() {
        return "Checking{" +
                "serviceCharge=" + serviceCharge +
                '}';
    }
}