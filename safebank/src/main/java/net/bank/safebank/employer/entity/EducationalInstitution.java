package net.bank.safebank.employer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ais_edu")
public class EducationalInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private Integer id;

    @Column(name = "ename", nullable = false)
    private String name;

    @OneToMany(mappedBy = "educationalInstitution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<StudentLoan> studentLoans;

    public EducationalInstitution() {
    }

    public EducationalInstitution(Integer id, String name, Set<StudentLoan> studentLoans) {
        this.id = id;
        this.name = name;
        this.studentLoans = studentLoans;
    }

    public Set<StudentLoan> getStudentLoans() {
        return studentLoans;
    }

    public void setStudentLoans(Set<StudentLoan> studentLoans) {
        this.studentLoans = studentLoans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EducationalInstitution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentLoans=" + studentLoans +
                '}';
    }
}