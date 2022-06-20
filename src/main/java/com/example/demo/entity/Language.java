package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            },
            mappedBy = "languages")
    @JsonIgnore
    private Set<Applicant> applicants = new HashSet<>();


    public Language(){
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setId(Long id) { this.id = id; }
    public void setName(String name){
        this.name = name;
    }

    public Set<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<Applicant> applicants){
        this.applicants = applicants;
    }

}


