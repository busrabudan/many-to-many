package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryRepo countryRepo;

    @GetMapping("/allCountry")
    public List<Country> getAllCountry(){
        return countryRepo.findAll();
    }
}
