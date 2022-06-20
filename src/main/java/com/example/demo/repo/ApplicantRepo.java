package com.example.demo.repo;

import com.example.demo.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {


    List<Applicant> findApplicantsByLanguagesId(Long languageId);

    //http://localhost:8080/applicants/user?userId=1
    @Query(value = "SELECT a FROM Applicant a WHERE a.user.id=:userId AND " +
            "(a.isActive=1 OR a.isActive IS NULL)")
    List<Applicant> findByUserId(Long userId);


    @Query(value = "SELECT * FROM applicant a " +
            "LEFT JOIN department d " +
            "ON d.id = a.department_id " +
            "LEFT JOIN applicant_language al " +
            "ON a.id = al.applicant_id " +
            "LEFT JOIN language  l " +
            "ON al.language_id = l.id " +
            "LEFT JOIN experience_years ey " +
            "ON a.experience_years_id=ey.id " +
            "WHERE d.name IN (:departmentList) " +
            "AND l.name IN (:languageList) " +
            "AND ey.years IN (:experinceList)",
            nativeQuery = true)
    List<Applicant> findApplicantByMultiSelect(List<String> languageList, List<String> departmentList, List<String> experinceList);
/*
    @Query(value = "select * from applicant a where id=:id",nativeQuery = true)
    List<Applicant> findByOneId(Long id);*/
}
