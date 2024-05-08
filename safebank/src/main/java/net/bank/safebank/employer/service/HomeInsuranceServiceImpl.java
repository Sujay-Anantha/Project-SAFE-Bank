package net.bank.safebank.employer.service;

import net.bank.safebank.employer.entity.EducationalInstitution;
import net.bank.safebank.employer.entity.HomeInsurance;
import net.bank.safebank.employer.repository.HomeInsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService{

    @Autowired
    private HomeInsuranceRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(HomeInsuranceServiceImpl.class);

    @Override
    public List<HomeInsurance> getAllHomeInsuranceDetails() {
        return repository.findAllHomeInsurances();
    }

    @Override
    public HomeInsurance getHomeInsuranceDetailsByHomeInsuranceID(Integer hID) {
        return repository.findHomeInsuranceById(hID);
    }

    @Override
    public HomeInsurance createHomeInsurance(HomeInsurance homeInsurance) {
        repository.createHomeInsurance(
                homeInsurance.getName(),
                homeInsurance.getStreet(),
                homeInsurance.getCity(),
                homeInsurance.getState(),
                homeInsurance.getZipcode()
        );
        return homeInsurance;
    }

    @Override
    public HomeInsurance updateHomeInsurance(Integer id, HomeInsurance homeInsurance) {
        repository.updateHomeInsurance(
                id,
                homeInsurance.getName(),
                homeInsurance.getStreet(),
                homeInsurance.getCity(),
                homeInsurance.getState(),
                homeInsurance.getZipcode()
        );
        return homeInsurance;
    }

    @Override
    public void deleteHomeInsurance(Integer id) {

    }
}
