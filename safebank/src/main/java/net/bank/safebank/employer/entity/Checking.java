package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("C")
@Table(name = "ais_checking")
public class Checking {
    @Id
    private Long acct_no;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal service_charge;

    public Checking() {
    }

    public Checking(BigDecimal service_charge) {
        this.service_charge = service_charge;
    }

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public BigDecimal getService_charge() {
        return service_charge;
    }

    public void setService_charge(BigDecimal service_charge) {
        this.service_charge = service_charge;
    }

    @Override
    public String toString() {
        return "Checking{" +
                "acct_no=" + acct_no +
                ", service_charge=" + service_charge +
                '}';
    }
}
