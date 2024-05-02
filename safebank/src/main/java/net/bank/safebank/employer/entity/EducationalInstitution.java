package net.bank.safebank.employer.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ais_edu")
public class EducationalInstitution {
    @Id
    private Integer eid;

    @Column(nullable = false, length = 30)
    private String ename;

    @OneToMany(mappedBy = "educationalInstitution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentLoan> studentLoans;

    public EducationalInstitution() {
    }

    public EducationalInstitution(String ename) {
        this.ename = ename;
    }

    // Getters and Setters

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public List<StudentLoan> getStudentLoans() {
        return studentLoans;
    }

    public void setStudentLoans(List<StudentLoan> studentLoans) {
        this.studentLoans = studentLoans;
    }

    @Override
    public String toString() {
        return "EducationalInstitution{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", studentLoans=" + studentLoans +
                '}';
    }
}
