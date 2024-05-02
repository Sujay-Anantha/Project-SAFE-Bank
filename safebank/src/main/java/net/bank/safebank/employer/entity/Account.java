package net.bank.safebank.employer.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "ais_acct")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "acct_type", discriminatorType = DiscriminatorType.STRING)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acct_no;

    @Column(nullable = false, length = 30)
    private String acct_name;

    @Column(nullable = false, length = 30)
    private String astreet;

    @Column(nullable = false, length = 30)
    private String acity;

    @Column(nullable = false, length = 2)
    private String astate;

    @Column(nullable = false)
    private int azipcode;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "date_opened", nullable = false)
    private LocalDateTime date_opened;

    @Column(nullable = false, length = 1)
    private String astatus;

    @Column(nullable = false, length = 8)
    private String acct_type;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    public Account() {
    }

    public Account(String acct_name, String astreet, String acity, String astate, int azipcode, LocalDateTime date_opened, String astatus, String acct_type) {
        this.acct_name = acct_name;
        this.astreet = astreet;
        this.acity = acity;
        this.astate = astate;
        this.azipcode = azipcode;
        this.date_opened = date_opened;
        this.astatus = astatus;
        this.acct_type = acct_type;
    }

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public String getAcct_name() {
        return acct_name;
    }

    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    public String getAstreet() {
        return astreet;
    }

    public void setAstreet(String astreet) {
        this.astreet = astreet;
    }

    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    public String getAstate() {
        return astate;
    }

    public void setAstate(String astate) {
        this.astate = astate;
    }

    public int getAzipcode() {
        return azipcode;
    }

    public void setAzipcode(int azipcode) {
        this.azipcode = azipcode;
    }

    public LocalDateTime getDate_opened() {
        return date_opened;
    }

    public void setDate_opened(LocalDateTime date_opened) {
        this.date_opened = date_opened;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public String getAcct_type() {
        return acct_type;
    }

    public void setAcct_type(String acct_type) {
        this.acct_type = acct_type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "acct_no=" + acct_no +
                ", acct_name='" + acct_name + '\'' +
                ", astreet='" + astreet + '\'' +
                ", acity='" + acity + '\'' +
                ", astate='" + astate + '\'' +
                ", azipcode=" + azipcode +
                ", date_opened=" + date_opened +
                ", astatus='" + astatus + '\'' +
                ", acct_type='" + acct_type + '\'' +
                ", customer=" + customer +
                '}';
    }
}
