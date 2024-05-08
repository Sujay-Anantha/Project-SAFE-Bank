package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.StudentLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLoanRepository extends JpaRepository<StudentLoan, Long> {
}
