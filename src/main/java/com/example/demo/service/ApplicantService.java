package com.example.demo.service;

import com.example.demo.entity.Applicant;
import com.example.demo.repo.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepo applicantRepo;


}
