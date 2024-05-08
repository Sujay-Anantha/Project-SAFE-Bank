package net.bank.safebank.employer.service;

import net.bank.safebank.employer.entity.EducationalInstitution;
import net.bank.safebank.employer.entity.HomeInsurance;

import java.util.List;

public interface HomeInsuranceService {
    List<HomeInsurance> getAllHomeInsuranceDetails();
    HomeInsurance getHomeInsuranceDetailsByHomeInsuranceID(Integer hID);
    HomeInsurance createHomeInsurance(HomeInsurance homeInsurance);
    HomeInsurance updateHomeInsurance(Integer id, HomeInsurance details);
    void deleteHomeInsurance(Integer id);
}
