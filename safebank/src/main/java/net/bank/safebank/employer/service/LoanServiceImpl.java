package net.bank.safebank.employer.service;

import net.bank.safebank.employer.entity.HomeLoan;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.PersonalLoan;
import net.bank.safebank.employer.entity.StudentLoan;
import net.bank.safebank.employer.repository.HomeLoanRepository;
import net.bank.safebank.employer.repository.LoanRepository;
import net.bank.safebank.employer.repository.PersonalLoanRepository;
import net.bank.safebank.employer.repository.StudentLoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LoanServiceImpl implements LoanService{
    @Autowired
    private LoanRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Loan getLoanDetailsByLoanNumber(Long loanNumber) {
        Loan loan = repository.findById(loanNumber)
                .orElseThrow(() -> new RuntimeException("Loan not present"));
        logger.debug("Loan details: {}", loan);

        // Log subclass-specific attributes
        if (loan instanceof HomeLoan) {
            HomeLoan homeLoan = (HomeLoan) loan;
            logger.debug("Home Loan - House Built Year: {}, Yearly Premium: {}", homeLoan.getHouseBuiltYear(), homeLoan.getYearlyPremium());
        } else if (loan instanceof PersonalLoan) {
            PersonalLoan personalLoan = (PersonalLoan) loan;
            logger.debug("Personal Loan - Early Repayment Fee: {}", personalLoan.getEarlyRepaymentFee());
        } else if (loan instanceof StudentLoan) {
            StudentLoan studentLoan = (StudentLoan) loan;
            logger.debug("Student Loan - Degree: {}, Institution: {}", studentLoan.getDegree(), studentLoan.getEducationalInstitution().getName());
        }

        return loan;
    }

//    @Override
//    public List<Loan> getAllLoanDetails() {
//        List<Loan> listOfLoans = repository.findAll();
//        listOfLoans.forEach(loan -> logger.debug("Loan No: {}, Type: {}", loan.getAccountNumber(), loan.getClass().getSimpleName()));
//        return listOfLoans;
//    }

    public List<Map<String, Object>> getAllLoanDetails() {
        List<Object[]> results = repository.findAllLoansWithDetails();
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("accountNumber", row[0]);
            rowMap.put("accountName", row[1]);
            rowMap.put("loanType", row[2]);
            rowMap.put("loanRate", row[3]);
            rowMap.put("loanAmount", row[4]);
            rowMap.put("loanPayment", row[5]);
            rowMap.put("loanMonths", row[6]);
            rowMap.put("additional1", row[7]);
            rowMap.put("additional2", row[8]);
            rowMap.put("additional3", row[9]);
            rowMap.put("additional4", row[10]);
            rowMap.put("additional5", row[11]);
            mappedResults.add(rowMap);
        }
        return mappedResults;
    }
    public List<HomeLoan> getHomeLoans() {
        return repository.findAllHomeLoans();
    }

    public List<StudentLoan> getStudentLoans() {
        return repository.findAllStudentLoans();
    }

    public List<PersonalLoan> getPersonalLoans() {
        return repository.findAllPersonalLoans();
    }
    public Optional<HomeLoan> getHomeLoanByAccountNumber(Long acctNo) {
        return repository.findHomeLoanByAccountNumber(acctNo);
    }

    public Optional<StudentLoan> getStudentLoanByAccountNumber(Long acctNo) {
        return repository.findStudentLoanByAccountNumber(acctNo);
    }

    public Optional<PersonalLoan> getPersonalLoanByAccountNumber(Long acctNo) {
        return repository.findPersonalLoanByAccountNumber(acctNo);
    }

    @Autowired
    private PersonalLoanRepository personalLoanRepository;
    @Autowired
    private HomeLoanRepository homeLoanRepository;
    @Autowired
    private StudentLoanRepository studentLoanRepository;

    public PersonalLoan createPersonalLoan(PersonalLoan personalLoan) {
        return personalLoanRepository.save(personalLoan);
    }

    public HomeLoan createHomeLoan(HomeLoan homeLoan) {
        return homeLoanRepository.save(homeLoan);
    }

    public StudentLoan createStudentLoan(StudentLoan studentLoan) {
        return studentLoanRepository.save(studentLoan);
    }

    public PersonalLoan updatePersonalLoan(Long loanId, PersonalLoan loanDetails) {
        PersonalLoan loan = repository.findById(loanId)
                .filter(PersonalLoan.class::isInstance)
                .map(PersonalLoan.class::cast)
                .orElseThrow(() -> new RuntimeException("Personal loan not found with id: " + loanId));

        loan.setLoanRate(loanDetails.getLoanRate());
        loan.setLoanAmount(loanDetails.getLoanAmount());
        loan.setLoanPayment(loanDetails.getLoanPayment());
        loan.setLoanMonths(loanDetails.getLoanMonths());
        loan.setEarlyRepaymentFee(loanDetails.getEarlyRepaymentFee());

        return repository.save(loan);
    }

    public HomeLoan updateHomeLoan(Long loanId, HomeLoan loanDetails) {
        HomeLoan loan = repository.findById(loanId)
                .filter(HomeLoan.class::isInstance)
                .map(HomeLoan.class::cast)
                .orElseThrow(() -> new RuntimeException("Home loan not found with id: " + loanId));

        loan.setLoanRate(loanDetails.getLoanRate());
        loan.setLoanAmount(loanDetails.getLoanAmount());
        loan.setLoanPayment(loanDetails.getLoanPayment());
        loan.setLoanMonths(loanDetails.getLoanMonths());
        loan.setHouseBuiltYear(loanDetails.getHouseBuiltYear());
        loan.setYearlyPremium(loanDetails.getYearlyPremium());
        loan.setHomeInsurance(loanDetails.getHomeInsurance());

        return repository.save(loan);
    }

    public StudentLoan updateStudentLoan(Long loanId, StudentLoan loanDetails) {
        StudentLoan loan = repository.findById(loanId)
                .filter(StudentLoan.class::isInstance)
                .map(StudentLoan.class::cast)
                .orElseThrow(() -> new RuntimeException("Student loan not found with id: " + loanId));

        loan.setLoanRate(loanDetails.getLoanRate());
        loan.setLoanAmount(loanDetails.getLoanAmount());
        loan.setLoanPayment(loanDetails.getLoanPayment());
        loan.setLoanMonths(loanDetails.getLoanMonths());
        loan.setEducationalInstitution(loanDetails.getEducationalInstitution());
        loan.setStudentId(loanDetails.getStudentId());
        loan.setDegree(loanDetails.getDegree());
        loan.setExpectedGraduationMonth(loanDetails.getExpectedGraduationMonth());
        loan.setExpectedGraduationYear(loanDetails.getExpectedGraduationYear());

        return repository.save(loan);
    }
}
