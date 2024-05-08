package net.bank.safebank;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.entity.StudentLoan;
import net.bank.safebank.employer.service.AccountService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanRetrivalTest {

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    public void testRetrieveStudentLoan() {
        // Assuming the account with ID 13003 is a StudentLoan in your test database
        Account account = accountService.getAccountByNumber(13003L);
        assertTrue(account instanceof StudentLoan, "The account should be an instance of StudentLoan");
        assertNotNull(((StudentLoan) account).getEducationalInstitution(), "Educational Institution should not be null");
    }
}

