package com.example.demo.controller;

import com.example.demo.entity.ExperienceYears;
import com.example.demo.repo.ExperienceYearsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/experienceYears")
public class ExperienceYearsController {

    @Autowired
    private ExperienceYearsRepo experienceYearsRepo;

    @GetMapping("/allExperienceYears")
    public List<ExperienceYears> getAllExperienceYears(){
        return experienceYearsRepo.findAll();
    }
}