package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
