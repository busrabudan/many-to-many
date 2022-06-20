package com.example.demo.repo;

import com.example.demo.entity.WorkingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingModelRepo extends JpaRepository<WorkingModel, Long> {
}
