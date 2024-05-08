package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.HomeLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeLoanRepository extends JpaRepository<HomeLoan, Long> {
}
