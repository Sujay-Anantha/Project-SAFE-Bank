package net.bank.safebank.employer.service;

import net.bank.safebank.employer.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomerDetails();
    Customer getCustomerDetailsByCustomerID(Long customerID);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customerDetails);
    void deleteCustomer(Long customerId);
}
