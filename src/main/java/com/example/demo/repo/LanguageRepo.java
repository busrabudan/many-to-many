package com.example.demo.repo;

import com.example.demo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo extends JpaRepository<Language,Long> {

    List<Language> findLanguagesByApplicantsId(Long applicantId);
}
