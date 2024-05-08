package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.HomeLoan;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.PersonalLoan;
import net.bank.safebank.employer.entity.StudentLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l WHERE TYPE(l) = :type")
    List<Loan> findAllByType(@Param("type") Class<? extends Loan> type);

    @Query(value = "SELECT l.acct_no, l.acct_name, l.ltype, l.lrate, l.lamount, l.lpayment, l.lmonths, " +
            "hl.hbuilt_year AS additional_1, hl.yr_premium AS additional_2, NULL AS additional_3, NULL AS additional_4, NULL AS additional_5 " +
            "FROM ais_loan l JOIN ais_home_loan hl ON l.acct_no = hl.acct_no WHERE l.ltype = 'HL' " +
            "UNION ALL " +
            "SELECT l.acct_no, l.acct_name, l.ltype, l.lrate, l.lamount, l.lpayment, l.lmonths, " +
            "sl.sid AS additional_1, sl.eid AS additional_2, sl.degree AS additional_3, sl.expgrad_month AS additional_4, sl.expgrad_year AS additional_5 " +
            "FROM ais_loan l JOIN ais_stud_loan sl ON l.acct_no = sl.acct_no WHERE l.ltype = 'SL' " +
            "UNION ALL " +
            "SELECT l.acct_no, l.acct_name, l.ltype, l.lrate, l.lamount, l.lpayment, l.lmonths, " +
            "pl.early_repay AS additional_1, NULL AS additional_2, NULL AS additional_3, NULL AS additional_4, NULL AS additional_5 " +
            "FROM ais_loan l JOIN ais_per_loan pl ON l.acct_no = pl.acct_no WHERE l.ltype = 'PL'", nativeQuery = true)
    List<Object[]> findAllLoansWithDetails();
    // Fetch only HomeLoan instances
    @Query("SELECT l FROM HomeLoan l")
    List<HomeLoan> findAllHomeLoans();

    // Fetch only StudentLoan instances
    @Query("SELECT l FROM StudentLoan l")
    List<StudentLoan> findAllStudentLoans();

    // Fetch only PersonalLoan instances
    @Query("SELECT l FROM PersonalLoan l")
    List<PersonalLoan> findAllPersonalLoans();

    @Query("SELECT l FROM HomeLoan l WHERE l.accountNumber = :acctNo")
    Optional<HomeLoan> findHomeLoanByAccountNumber(@Param("acctNo") Long acctNo);

    @Query("SELECT l FROM StudentLoan l WHERE l.accountNumber = :acctNo")
    Optional<StudentLoan> findStudentLoanByAccountNumber(@Param("acctNo") Long acctNo);

    @Query("SELECT l FROM PersonalLoan l WHERE l.accountNumber = :acctNo")
    Optional<PersonalLoan> findPersonalLoanByAccountNumber(@Param("acctNo") Long acctNo);
}


