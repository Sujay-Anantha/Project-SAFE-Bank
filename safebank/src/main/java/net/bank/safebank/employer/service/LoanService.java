package net.bank.safebank.employer.service;
import net.bank.safebank.employer.entity.HomeLoan;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.PersonalLoan;
import net.bank.safebank.employer.entity.StudentLoan;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LoanService {

    //List<Loan> getAllLoanDetails();
    Loan getLoanDetailsByLoanNumber(Long loanNumber);
    List<Map<String, Object>> getAllLoanDetails();
    List<HomeLoan> getHomeLoans();
    List<StudentLoan> getStudentLoans();
    List<PersonalLoan> getPersonalLoans();
    Optional<HomeLoan> getHomeLoanByAccountNumber(Long acctNo);
    Optional<StudentLoan> getStudentLoanByAccountNumber(Long acctNo);
    Optional<PersonalLoan> getPersonalLoanByAccountNumber(Long acctNo);
    StudentLoan createStudentLoan(StudentLoan studentLoan);
    HomeLoan createHomeLoan(HomeLoan homeLoan);
    PersonalLoan createPersonalLoan(PersonalLoan personalLoan);
    PersonalLoan updatePersonalLoan(Long loanId, PersonalLoan loanDetails);
    HomeLoan updateHomeLoan(Long loanId, HomeLoan loanDetails);
    StudentLoan updateStudentLoan(Long loanId, StudentLoan loanDetails);
}
