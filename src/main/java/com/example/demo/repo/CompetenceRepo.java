package com.example.demo.repo;

import com.example.demo.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepo extends JpaRepository<Competence, Long> {
}
