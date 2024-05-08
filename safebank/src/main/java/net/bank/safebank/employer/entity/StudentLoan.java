package net.bank.safebank.employer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ais_stud_loan")
@DiscriminatorValue("SL")
public class StudentLoan extends Loan {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eid")
    private EducationalInstitution educationalInstitution;

    @Column(name = "sid", nullable = false)
    private Integer studentId;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "expgrad_month", nullable = false)
    private Byte expectedGraduationMonth;

    @Column(name = "expgrad_year", nullable = false)
    private Integer expectedGraduationYear;

    public StudentLoan(){
        super();
    }

    public StudentLoan(BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, EducationalInstitution educationalInstitution, Integer studentId, String degree, Byte expectedGraduationMonth, Integer expectedGraduationYear) {
        super(loanRate, loanAmount, loanPayment, loanMonths);
        this.educationalInstitution = educationalInstitution;
        this.studentId = studentId;
        this.degree = degree;
        this.expectedGraduationMonth = expectedGraduationMonth;
        this.expectedGraduationYear = expectedGraduationYear;
    }

    public StudentLoan(Long accountNumber, String accountName, String street, String city, String state, Integer zipcode, Date dateOpened, String status, String accountType, Customer customer, BigDecimal loanRate, BigDecimal loanAmount, BigDecimal loanPayment, Short loanMonths, EducationalInstitution educationalInstitution, Integer studentId, String degree, Byte expectedGraduationMonth, Integer expectedGraduationYear) {
        super(accountNumber, accountName, street, city, state, zipcode, dateOpened, status, accountType, customer, loanRate, loanAmount, loanPayment, loanMonths);
        this.educationalInstitution = educationalInstitution;
        this.studentId = studentId;
        this.degree = degree;
        this.expectedGraduationMonth = expectedGraduationMonth;
        this.expectedGraduationYear = expectedGraduationYear;
    }

    public EducationalInstitution getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Byte getExpectedGraduationMonth() {
        return expectedGraduationMonth;
    }

    public void setExpectedGraduationMonth(Byte expectedGraduationMonth) {
        this.expectedGraduationMonth = expectedGraduationMonth;
    }

    public Integer getExpectedGraduationYear() {
        return expectedGraduationYear;
    }

    public void setExpectedGraduationYear(Integer expectedGraduationYear) {
        this.expectedGraduationYear = expectedGraduationYear;
    }

    @Override
    public String toString() {
        return "StudentLoan{" +
                "educationalInstitution=" + educationalInstitution +
                ", studentId=" + studentId +
                ", degree='" + degree + '\'' +
                ", expectedGraduationMonth=" + expectedGraduationMonth +
                ", expectedGraduationYear=" + expectedGraduationYear +
                '}';
    }
}

