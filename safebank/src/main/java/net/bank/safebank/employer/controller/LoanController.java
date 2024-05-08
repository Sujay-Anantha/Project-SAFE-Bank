package net.bank.safebank.employer.controller;

import net.bank.safebank.employer.entity.HomeLoan;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.PersonalLoan;
import net.bank.safebank.employer.entity.StudentLoan;
import net.bank.safebank.employer.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

//    @GetMapping("/{loanNumber}")
//    public ResponseEntity<Loan> getLoanByNumber(@PathVariable Long loanNumber) {
//        try {
//            Loan loan = loanService.getLoanDetailsByLoanNumber(loanNumber);
//            return ResponseEntity.ok(loan);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{loanNumber}")
    public ResponseEntity<Loan> getLoanByNumber(@PathVariable Long loanNumber) {
        try {
            Loan loan = loanService.getLoanDetailsByLoanNumber(loanNumber);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Loan>> getAllLoans() {
//        List<Loan> loans = loanService.getAllLoanDetails();
//        return ResponseEntity.ok(loans);
//    }

    @GetMapping("/details")
    public ResponseEntity<List<Map<String, Object>>> getLoanDetails() {
        List<Map<String, Object>> loanDetails = loanService.getAllLoanDetails();
        return ResponseEntity.ok(loanDetails);
    }
    @GetMapping("/homeLoans")
    public List<HomeLoan> getHomeLoans() {
        return loanService.getHomeLoans();
    }

    @GetMapping("/studentLoans")
    public List<StudentLoan> getStudentLoans() {
        return loanService.getStudentLoans();
    }

    @GetMapping("/personalLoans")
    public List<PersonalLoan> getPersonalLoans() {
        return loanService.getPersonalLoans();
    }
    @GetMapping("/homeLoans/{acctNo}")
    public ResponseEntity<HomeLoan> getHomeLoan(@PathVariable Long acctNo) {
        return loanService.getHomeLoanByAccountNumber(acctNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/studentLoans/{acctNo}")
    public ResponseEntity<StudentLoan> getStudentLoan(@PathVariable Long acctNo) {
        return loanService.getStudentLoanByAccountNumber(acctNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/personalLoans/{acctNo}")
    public ResponseEntity<PersonalLoan> getPersonalLoan(@PathVariable Long acctNo) {
        return loanService.getPersonalLoanByAccountNumber(acctNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createpersonalloan")
    public ResponseEntity<PersonalLoan> createPersonalLoan(@RequestBody PersonalLoan personalLoan) {
        PersonalLoan createdPersonalLoan = loanService.createPersonalLoan(personalLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPersonalLoan);
    }

    @PostMapping("/createhomeloan")
    public ResponseEntity<HomeLoan> createHomeLoan(@RequestBody HomeLoan homeLoan) {
        HomeLoan createdHomeLoan = loanService.createHomeLoan(homeLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHomeLoan);
    }

    @PostMapping("/createstudentloan")
    public ResponseEntity<StudentLoan> createStudentLoan(@RequestBody StudentLoan studentLoan) {
        StudentLoan createdStudentLoan = loanService.createStudentLoan(studentLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentLoan);
    }

    @PutMapping("/updatepersonalloan/{loanId}")
    public ResponseEntity<PersonalLoan> updatePersonalLoan(@PathVariable Long loanId, @RequestBody PersonalLoan loanDetails) {
        PersonalLoan updatedLoan = loanService.updatePersonalLoan(loanId, loanDetails);
        return ResponseEntity.ok(updatedLoan);
    }

    @PutMapping("/updatehomeloan/{loanId}")
    public ResponseEntity<HomeLoan> updateHomeLoan(@PathVariable Long loanId, @RequestBody HomeLoan loanDetails) {
        HomeLoan updatedLoan = loanService.updateHomeLoan(loanId, loanDetails);
        return ResponseEntity.ok(updatedLoan);
    }

    @PutMapping("/updatestudentloan/{loanId}")
    public ResponseEntity<StudentLoan> updateStudentLoan(@PathVariable Long loanId, @RequestBody StudentLoan loanDetails) {
        StudentLoan updatedLoan = loanService.updateStudentLoan(loanId, loanDetails);
        return ResponseEntity.ok(updatedLoan);
    }
}

