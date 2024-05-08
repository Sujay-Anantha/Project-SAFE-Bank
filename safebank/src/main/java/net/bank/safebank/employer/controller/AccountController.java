package net.bank.safebank.employer.controller;

import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.entity.Checking;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.Savings;
import net.bank.safebank.employer.service.AccountService;
import net.bank.safebank.employer.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/getall")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable Long accountNo) {
        Account account = accountService.getAccountByNumber(accountNo);
        return account != null ? new ResponseEntity<>(account, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{accountNo}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long accountNo, @RequestBody Account account) {
        accountService.updateAccount(accountNo, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{accountNo}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountNo) {
        accountService.deleteAccount(accountNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping("/create")
//    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
//        Account createdAccount = service.saveAccount(account);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
//    }


//    @PostMapping("/createChecking")
//    public ResponseEntity<Checking> createChecking(@RequestBody Checking checking) {
//        Checking createdChecking = service.createChecking(checking);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdChecking);
//    }
//
//    @PostMapping("/createSavings")
//    public ResponseEntity<Savings> createSavings(@RequestBody Savings savings) {
//        Savings createdSavings = service.createSavings(savings);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdSavings);
//    }
//
//    @PostMapping("/createLoan")
//    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
//        Loan createdLoan = service.createLoan(loan);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
//    }
//
//    @PutMapping("/updatechecking/{id}")
//    public ResponseEntity<Checking> updateCheckingAccount(@PathVariable Long id, @RequestBody Checking accountDetails) {
//        Checking updatedAccount = service.updateCheckingAccount(id, accountDetails);
//        return ResponseEntity.ok(updatedAccount);
//    }
//
//    @PutMapping("/updatesavings/{id}")
//    public ResponseEntity<Savings> updateSavingsAccount(@PathVariable Long id, @RequestBody Savings accountDetails) {
//        Savings updatedAccount = service.updateSavingsAccount(id, accountDetails);
//        return ResponseEntity.ok(updatedAccount);
//    }
//
//    @PutMapping("/updateloan/{id}")
//    public ResponseEntity<Loan> updateLoanAccount(@PathVariable Long id, @RequestBody Loan accountDetails) {
//        Loan updatedAccount = service.updateLoanAccount(id, accountDetails);
//        return ResponseEntity.ok(updatedAccount);
//    }
}


