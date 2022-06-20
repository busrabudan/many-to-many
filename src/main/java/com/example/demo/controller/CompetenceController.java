package com.example.demo.controller;

import com.example.demo.entity.Competence;
import com.example.demo.repo.CompetenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/competences")
public class CompetenceController {
    @Autowired
    private CompetenceRepo competenceRepo;

    @GetMapping("/allCompetence")
    public List<Competence> getAllCompetence(){
        return competenceRepo.findAll();
    }
}
