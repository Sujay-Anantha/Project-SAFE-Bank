package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.PersonalLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalLoanRepository extends JpaRepository<PersonalLoan, Long> {
}
