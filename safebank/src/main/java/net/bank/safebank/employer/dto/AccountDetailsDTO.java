package net.bank.safebank.employer.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDetailsDTO {
    private Long accountNumber;
    private String accountName;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
    private Date dateOpened;
    private String status;
    private Long customerId;
    private String customerName;
    private BigDecimal serviceCharge;  // Specific to Checking
    private BigDecimal interestRate;   // Specific to Savings
    private BigDecimal loanRate;       // Specific to Loan
    private BigDecimal loanAmount;     // Specific to Loan
    private BigDecimal loanPayment;    // Specific to Loan
    private Short loanMonths;          // Specific to Loan

    // Constructor and getters/setters to follow

    public AccountDetailsDTO(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, Long customerId, String customerName, BigDecimal serviceCharge, BigDecimal interestRate, BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.dateOpened = dateOpened;
        this.status = status;
        this.customerId = customerId;
        this.customerName = customerName;
        this.serviceCharge = serviceCharge;
        this.interestRate = interestRate;
        this.loanRate = loanRate;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanMonths = loanMonths;
    }

    public AccountDetailsDTO() {
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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
        return "AccountDetailsDTO{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", dateOpened=" + dateOpened +
                ", status='" + status + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", serviceCharge=" + serviceCharge +
                ", interestRate=" + interestRate +
                ", loanRate=" + loanRate +
                ", loanAmount=" + loanAmount +
                ", loanPayment=" + loanPayment +
                ", loanMonths=" + loanMonths +
                '}';
    }
}