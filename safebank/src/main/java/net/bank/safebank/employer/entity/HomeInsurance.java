package net.bank.safebank.employer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ais_home_insr")
public class HomeInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hid")
    private Integer id;

    @Column(name = "hname", nullable = false)
    private String name;

    @Column(name = "hstreet", nullable = false)
    private String street;

    @Column(name = "hcity", nullable = false)
    private String city;

    @Column(name = "hstate", nullable = false)
    private String state;

    @Column(name = "hzipcode", nullable = false)
    private Integer zipcode;

    @OneToMany(mappedBy = "homeInsurance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HomeLoan> homeLoans;

    public HomeInsurance() {
    }

    public HomeInsurance(Integer id, String name, String street, String city, String state, Integer zipcode, Set<HomeLoan> homeLoans) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.homeLoans = homeLoans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<HomeLoan> getHomeLoans() {
        return homeLoans;
    }

    public void setHomeLoans(Set<HomeLoan> homeLoans) {
        this.homeLoans = homeLoans;
    }

    @Override
    public String toString() {
        return "HomeInsurance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", homeLoans=" + homeLoans +
                '}';
    }
}
