package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Long> {

}
