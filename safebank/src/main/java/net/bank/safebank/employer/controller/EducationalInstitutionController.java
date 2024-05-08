package net.bank.safebank.employer.controller;


import net.bank.safebank.employer.entity.EducationalInstitution;
import net.bank.safebank.employer.service.EducationalInstitutionService;
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
@RequestMapping("/educationalinstitution")
public class EducationalInstitutionController {

    @Autowired
    private EducationalInstitutionService service;

    @GetMapping("/getall")
    public ResponseEntity<List<EducationalInstitution>> getAllEducationalInstitutions() {
        return new ResponseEntity<>(service.getAllEducationalInstitutions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalInstitution> getEducationalInstitutionById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getEducationalInstitutionById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEducationalInstitution(@RequestBody EducationalInstitution educationalInstitution) {
        service.createEducationalInstitution(educationalInstitution);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value ="/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEducationalInstitution(
            @PathVariable("id") Integer id, @RequestBody EducationalInstitution educationalInstitution) {
        service.updateEducationalInstitution(id, educationalInstitution);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEducationalInstitution(@PathVariable("id") Integer id) {
        service.deleteEducationalInstitution(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
