package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.repo.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityRepo universityRepo;

    @GetMapping("allUniversity")
    public List<University> getAllUniversity(){
        return universityRepo.findAll();
    }
}