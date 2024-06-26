package net.bank.safebank.employer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "ais_acct")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "acct_type", discriminatorType = DiscriminatorType.STRING)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acct_no")
    private Long accountNumber;

    @Column(name = "acct_name", nullable = false)
    private String accountName;

    @Column(name = "astreet", nullable = false)
    private String street;

    @Column(name = "acity", nullable = false)
    private String city;

    @Column(name = "astate", nullable = false)
    private String state;

    @Column(name = "azipcode", nullable = false)
    private Integer zipcode;

    @Column(name = "date_opened", nullable = false)
    private java.util.Date dateOpened;

    @Column(name = "astatus", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custid", nullable = false)
    @JsonBackReference
    private Customer customer;

    public Account() {
    }

    public Account(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.dateOpened = dateOpened;
        this.status = status;
        this.customer = customer;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", dateOpened=" + dateOpened +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                '}';
    }
}
