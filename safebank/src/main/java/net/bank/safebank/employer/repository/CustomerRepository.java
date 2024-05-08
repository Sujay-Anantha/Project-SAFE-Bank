package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "CALL GetAllCustomers();", nativeQuery = true)
    List<Customer> findAllCustomers();

    @Query(value = "CALL GetCustomerByID(:custId);", nativeQuery = true)
    Customer findCustomerById(@Param("custId") Long customerId);

    @Transactional
    @Modifying
    @Query(value = "CALL CreateCustomer(:cfname, :clname, :cstreet, :ccity, :cstate, :czipcode);", nativeQuery = true)
    void createCustomer(
            @Param("cfname") String firstName,
            @Param("clname") String lastName,
            @Param("cstreet") String street,
            @Param("ccity") String city,
            @Param("cstate") String state,
            @Param("czipcode") Integer zipcode
    );

    @Transactional
    @Modifying
    @Query(value = "CALL UpdateCustomer(:custId, :cfname, :clname, :cstreet, :ccity, :cstate, :czipcode);", nativeQuery = true)
    void updateCustomer(
            @Param("custId") Long customerId,
            @Param("cfname") String firstName,
            @Param("clname") String lastName,
            @Param("cstreet") String street,
            @Param("ccity") String city,
            @Param("cstate") String state,
            @Param("czipcode") Integer zipcode
    );

    @Modifying
    @Transactional
    @Query(value = "CALL DeleteCustomer(:custId);", nativeQuery = true)
    void deleteCustomer(@Param("custId") Long customerId);
}

