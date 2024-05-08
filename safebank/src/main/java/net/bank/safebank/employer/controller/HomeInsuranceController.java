package net.bank.safebank.employer.controller;

import net.bank.safebank.employer.entity.EducationalInstitution;
import net.bank.safebank.employer.entity.HomeInsurance;
import net.bank.safebank.employer.service.HomeInsuranceService;
import net.bank.safebank.employer.service.HomeInsuranceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/homeinsurance")
public class HomeInsuranceController {

    @Autowired
    HomeInsuranceService service;

    private static final Logger logger = LoggerFactory.getLogger(HomeInsuranceController.class);

    @GetMapping("/getall")
    public ResponseEntity<List<HomeInsurance>> getHomeInsurances() {
        return new ResponseEntity<>(service.getAllHomeInsuranceDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeInsurance> getHomeInsuranceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getHomeInsuranceDetailsByHomeInsuranceID(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createHomeInsurance(@RequestBody HomeInsurance homeInsurance) {
        service.createHomeInsurance(homeInsurance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value ="/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateHomeInsurance(
            @PathVariable("id") Integer id, @RequestBody HomeInsurance homeInsurance) {
        service.updateHomeInsurance(id, homeInsurance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHomeInsurance(@PathVariable("id") Integer id) {
        service.deleteHomeInsurance(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
