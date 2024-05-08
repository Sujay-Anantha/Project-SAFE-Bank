package net.bank.safebank.employer.service;


import net.bank.safebank.employer.entity.EducationalInstitution;


import java.util.List;


public interface EducationalInstitutionService {
    List<EducationalInstitution> getAllEducationalInstitutions();
    EducationalInstitution getEducationalInstitutionById(Integer eid);
    void createEducationalInstitution(EducationalInstitution educationalInstitution);
    void updateEducationalInstitution(Integer eid, EducationalInstitution educationalInstitution);
    void deleteEducationalInstitution(Integer eid);
}
