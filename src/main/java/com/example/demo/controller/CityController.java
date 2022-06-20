package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/citys")
public class CityController {
    @Autowired
    private CityRepo cityRepo;

    @GetMapping("/allCity")
    public List<City> getAllCity(){
        return cityRepo.findAll();
    }
}
