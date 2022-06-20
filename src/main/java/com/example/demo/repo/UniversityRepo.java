package com.example.demo.repo;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepo extends JpaRepository<University, Long> {
}
