package net.bank.safebank.employer.dto;

import java.util.Date;

public class AccountDTO {
    private Long accountNumber;
    private String accountName;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
    private java.util.Date dateOpened;
    private String status;

    // Constructors


    public AccountDTO() {
    }

    public AccountDTO(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.dateOpened = dateOpened;
        this.status = status;
    }

    // Getters and Setters

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

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", dateOpened=" + dateOpened +
                ", status='" + status + '\'' +
                '}';
    }
}
