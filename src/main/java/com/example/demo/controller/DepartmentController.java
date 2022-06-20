package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/allDepartment")
    public List<Department> getAllDepartment(){
        return departmentRepo.findAll();
    }
}