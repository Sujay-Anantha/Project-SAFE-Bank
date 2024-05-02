package net.bank.safebank.employer.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SL")
@Table(name = "ais_stud_loan")
public class StudentLoan {
    @Id
    private Long acct_no;

    @Column(nullable = false)
    private Integer sid;

    @Column(nullable = false, length = 1)
    private String degree;

    @Column(nullable = false)
    private Byte expgrad_month;

    @Column(nullable = false)
    private Integer expgrad_year;

    @ManyToOne
    @JoinColumn(name = "eid", nullable = false)
    private EducationalInstitution educationalInstitution;

    public StudentLoan() {
    }

    public StudentLoan(Integer sid, String degree, Byte expgrad_month, Integer expgrad_year) {
        this.sid = sid;
        this.degree = degree;
        this.expgrad_month = expgrad_month;
        this.expgrad_year = expgrad_year;
    }

    // Getters and Setters

    public Long getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(Long acct_no) {
        this.acct_no = acct_no;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Byte getExpgrad_month() {
        return expgrad_month;
    }

    public void setExpgrad_month(Byte expgrad_month) {
        this.expgrad_month = expgrad_month;
    }

    public Integer getExpgrad_year() {
        return expgrad_year;
    }

    public void setExpgrad_year(Integer expgrad_year) {
        this.expgrad_year = expgrad_year;
    }

    public EducationalInstitution getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    @Override
    public String toString() {
        return "StudentLoan{" +
                "acct_no=" + acct_no +
                ", sid=" + sid +
                ", degree='" + degree + '\'' +
                ", expgrad_month=" + expgrad_month +
                ", expgrad_year=" + expgrad_year +
                ", educationalInstitution=" + educationalInstitution +
                '}';
    }
}
