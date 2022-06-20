package com.example.demo.controller;

import com.example.demo.entity.WorkingModel;
import com.example.demo.repo.WorkingModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/workingModels")
public class WorkingModelController {

    @Autowired
    private WorkingModelRepo workingModelRepo;

    @GetMapping("/allWorkingModel")
    public List<WorkingModel> getAllWorkingModel(){
        return workingModelRepo.findAll();
    }
}
