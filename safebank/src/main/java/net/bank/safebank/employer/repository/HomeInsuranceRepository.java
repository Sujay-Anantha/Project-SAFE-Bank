package net.bank.safebank.employer.repository;


import net.bank.safebank.employer.entity.HomeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance, Long> {
    @Query(value = "CALL getAllHomeInsurance()", nativeQuery = true)
    List<HomeInsurance> findAllHomeInsurances();

    @Query(value = "CALL GetHomeInsuranceByID(:hid)", nativeQuery = true)
    HomeInsurance findHomeInsuranceById(@Param("hid") Integer hid);

    @Modifying
    @Transactional
    @Query(value = "CALL CreateHomeInsurance(:hname, :hstreet, :hcity, :hstate, :hzipcode)", nativeQuery = true)
    void createHomeInsurance(@Param("hname") String hname,
                             @Param("hstreet") String hstreet,
                             @Param("hcity") String hcity,
                             @Param("hstate") String hstate,
                             @Param("hzipcode") Integer hzipcode);

    @Modifying
    @Transactional
    @Query(value = "CALL UpdateHomeInsurance(:hid, :hname, :hstreet, :hcity, :hstate, :hzipcode)", nativeQuery = true)
    void updateHomeInsurance(@Param("hid") Integer hid,
                             @Param("hname") String hname,
                             @Param("hstreet") String hstreet,
                             @Param("hcity") String hcity,
                             @Param("hstate") String hstate,
                             @Param("hzipcode") Integer hzipcode);

    @Modifying
    @Transactional
    @Query(value = "CALL DeleteHomeInsurance(:hid)", nativeQuery = true)
    void deleteHomeInsurance(@Param("hid") Integer hid);
}
