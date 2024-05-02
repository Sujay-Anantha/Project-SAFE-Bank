package net.bank.safebank.employer.controller;

import net.bank.safebank.employer.dto.AccountUpdateDTO;
import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createAccount = service.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount) ;
    }

    @GetMapping("/{acct_no}")
    public Account getAccountByAccountNumber(@PathVariable Long acct_no){
        Account account = service.getAccountDetailsByAccountNumber(acct_no);
        return account;
    }

    @GetMapping("/getallaccount")
    public List<Account> getAllAccountDetails(){
        List<Account> allAccountDetails = service.getAllAccountDetails();
        return allAccountDetails;
    }
    @PutMapping("/update/{acct_no}")
    public ResponseEntity<Account> updateAccountDetails(@PathVariable Long acct_no, @RequestBody AccountUpdateDTO accountUpdateDTO) {

        Account updatedAccount = service.updateAccountDetails(acct_no, accountUpdateDTO);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/delete/{acct_no}")
    public ResponseEntity deleteAccount(@PathVariable Long acct_no){

        service.closeAccount(acct_no);
        return ResponseEntity.ok("Account closed.");
    }


}
