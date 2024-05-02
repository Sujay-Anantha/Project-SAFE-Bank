package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("L")
@Table(name = "ais_loan")
public class Loan {
    @Id
    private Long acct_no;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal lrate;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal lamount;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal lpayment;

    @Column(nullable = false)
    private Short lmonths;

    @Column(nullable = false, length = 1)
    private String ltype;

    public Loan() {
    }

    public Loan(BigDecimal lrate, BigDecimal lamount, BigDecimal lpayment, Short lmonths, String ltype) {
        this.lrate = lrate;
        this.lamount = lamount;
        this.lpayment = lpayment;
        this.lmonths = lmonths;
        this.ltype = ltype;
    }

    // Getters and Setters

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public BigDecimal getLrate() {
        return lrate;
    }

    public void setLrate(BigDecimal lrate) {
        this.lrate = lrate;
    }

    public BigDecimal getLamount() {
        return lamount;
    }

    public void setLamount(BigDecimal lamount) {
        this.lamount = lamount;
    }

    public BigDecimal getLpayment() {
        return lpayment;
    }

    public void setLpayment(BigDecimal lpayment) {
        this.lpayment = lpayment;
    }

    public Short getLmonths() {
        return lmonths;
    }

    public void setLmonths(Short lmonths) {
        this.lmonths = lmonths;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "acct_no=" + acct_no +
                ", lrate=" + lrate +
                ", lamount=" + lamount +
                ", lpayment=" + lpayment +
                ", lmonths=" + lmonths +
                ", ltype='" + ltype + '\'' +
                '}';
    }
}
