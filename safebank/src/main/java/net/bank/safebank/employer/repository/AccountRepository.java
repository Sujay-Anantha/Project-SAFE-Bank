package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.dto.AccountDetailsDTO;
import net.bank.safebank.employer.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "CALL GetAllAccounts()", nativeQuery = true)
    List<Account> findAllAccounts();

    @Query(value = "CALL GetAccountByNumber(:acctNo)", nativeQuery = true)
    Account findAccountByNumber(@Param("acctNo") Long acctNo);

    @Modifying
    @Transactional
    @Query(value = "CALL CreateAccount(:acctName, :astreet, :acity, :astate, :azipcode, :dateOpened, :astatus, :acctType, :custId)", nativeQuery = true)
    void createAccount(
            @Param("acctName") String acctName,
            @Param("astreet") String astreet,
            @Param("acity") String acity,
            @Param("astate") String astate,
            @Param("azipcode") Integer azipcode,
            @Param("dateOpened") String dateOpened,
            @Param("astatus") String astatus,
            @Param("acctType") String acctType,
            @Param("custId") Long custId
    );

    @Modifying
    @Transactional
    @Query(value = "CALL UpdateAccount(:acctNo, :acctName, :astreet, :acity, :astate, :azipcode, :dateOpened, :astatus)", nativeQuery = true)
    void updateAccount(
            @Param("acctNo") Long acctNo,
            @Param("acctName") String acctName,
            @Param("astreet") String astreet,
            @Param("acity") String acity,
            @Param("astate") String astate,
            @Param("azipcode") Integer azipcode,
            @Param("dateOpened") String dateOpened,
            @Param("astatus") String astatus
    );

    @Modifying
    @Transactional
    @Query(value = "CALL DeleteAccount(:acctNo)", nativeQuery = true)
    void deleteAccount(@Param("acctNo") Long acctNo);
}