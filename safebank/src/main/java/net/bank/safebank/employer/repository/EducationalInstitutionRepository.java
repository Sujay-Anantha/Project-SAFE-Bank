package net.bank.safebank.employer.repository;

import net.bank.safebank.employer.entity.EducationalInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EducationalInstitutionRepository extends JpaRepository<EducationalInstitution, Long> {
    @Query(value = "CALL GetAllEducationalInstitutions()", nativeQuery = true)
    List<EducationalInstitution> findAllEducationalInstitutions();

    @Query(value = "CALL GetEducationalInstitutionByID(:eid)", nativeQuery = true)
    EducationalInstitution findEducationalInstitutionById(@Param("eid") Integer eid);

    @Modifying
    @Transactional
    @Query(value = "CALL CreateEducationalInstitution(:ename)", nativeQuery = true)
    void createEducationalInstitution(@Param("ename") String ename);

    @Modifying
    @Transactional
    @Query(value = "CALL UpdateEducationalInstitution(:eid, :ename)", nativeQuery = true)
    void updateEducationalInstitution(
            @Param("eid") Integer eid,
            @Param("ename") String ename
    );

    @Modifying
    @Transactional
    @Query(value = "CALL DeleteEducationalInstitution(:eid)", nativeQuery = true)
    void deleteEducationalInstitution(@Param("eid") Integer eid);
}
