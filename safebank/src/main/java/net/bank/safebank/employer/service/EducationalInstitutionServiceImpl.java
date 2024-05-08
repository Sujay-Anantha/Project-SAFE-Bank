package net.bank.safebank.employer.service;


import net.bank.safebank.employer.entity.Customer;
import net.bank.safebank.employer.entity.EducationalInstitution;
import net.bank.safebank.employer.repository.EducationalInstitutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationalInstitutionServiceImpl implements EducationalInstitutionService{
    @Autowired
    private EducationalInstitutionRepository repository;

    @Override
    public List<EducationalInstitution> getAllEducationalInstitutions() {
        return repository.findAllEducationalInstitutions();
    }

    @Override
    public EducationalInstitution getEducationalInstitutionById(Integer eid) {
        return repository.findEducationalInstitutionById(eid);
    }

    @Override
    public void createEducationalInstitution(EducationalInstitution educationalInstitution) {
        repository.createEducationalInstitution(educationalInstitution.getName());
    }

    @Override
    public void updateEducationalInstitution(Integer eid, EducationalInstitution educationalInstitution) {
        repository.updateEducationalInstitution(eid, educationalInstitution.getName());
    }

    @Override
    public void deleteEducationalInstitution(Integer eid) {
        repository.deleteEducationalInstitution(eid);
    }
}
