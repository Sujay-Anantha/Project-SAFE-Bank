package net.bank.safebank.employer.service;

import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.entity.Customer;
import net.bank.safebank.employer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerRepository.findAllCustomers();
    }

    @Override
    public Customer getCustomerDetailsByCustomerID(Long customerID) {
        return customerRepository.findCustomerById(customerID);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customerRepository.createCustomer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getState(),
                customer.getZipcode()
        );
        return customer;  // Returning the original entity without database ID update due to procedure limitations.
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        customerRepository.updateCustomer(
                customerId,
                customerDetails.getFirstName(),
                customerDetails.getLastName(),
                customerDetails.getStreet(),
                customerDetails.getCity(),
                customerDetails.getState(),
                customerDetails.getZipcode()
        );
        return customerDetails;  // Returning the provided entity without database ID update due to procedure limitations.
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteCustomer(customerId);
    }
//    @Override
//    public void deleteCustomer(Long customerId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
//
//        customerRepository.delete(customer);
//        logger.debug("Deleted customer: {}", customerId);
//    }

}
