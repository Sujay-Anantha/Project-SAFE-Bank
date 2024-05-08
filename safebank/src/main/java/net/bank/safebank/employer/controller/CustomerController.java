package net.bank.safebank.employer.controller;


import net.bank.safebank.employer.entity.Customer;
import net.bank.safebank.employer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/getallcustomer")
    public List<Customer> getAllCustomerDetails(){
        List<Customer> allCustomerDetails= service.getAllCustomerDetails();
        return allCustomerDetails;
    }

    @GetMapping("/{custid}")
    public Customer getCustomerDetailsByCustomerID(@PathVariable Long custid){
        Customer customer = service.getCustomerDetailsByCustomerID(custid);
        return customer;
    }
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    @PutMapping("/update/{custid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long custid, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = service.updateCustomer(custid, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }
    @DeleteMapping("/delete/{custid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long custid) {
        try {
            service.deleteCustomer(custid);
            String message = String.format("Customer with ID %d has been deleted successfully.", custid);
            return ResponseEntity.ok(Collections.singletonMap("message", message));
        } catch (RuntimeException ex) {
            String error = String.format("Failed to delete customer with ID %d: %s", custid, ex.getMessage());
            logger.error(error);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", error));
        }
    }
}
