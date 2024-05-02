package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
