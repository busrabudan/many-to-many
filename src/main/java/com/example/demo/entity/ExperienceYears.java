package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "experience_years")
public class ExperienceYears {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String years;
}