package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.*;
import com.example.demo.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantRepo applicantRepo;
    private ApplicantService applicantService;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private UniversityRepo universityReppo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ExperienceYearsRepo experienceYearsRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants(){
    List<Applicant> applicants = new ArrayList<Applicant>();
    // buraya eklenecek kısımlar var
    applicants = applicantRepo.findAll();
        return applicants;
    }

    @GetMapping("/user") //login olan userın Idsine göre listeleme
    public List<Applicant> getOneUser(@RequestParam("userId") Long userId){
        return applicantRepo.findByUserId(userId);
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<Applicant> getApplicantsById(@PathVariable("id") Long id){
        Applicant applicant = applicantRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Applicant with id = " + id));
        return new ResponseEntity<>(applicant,HttpStatus.OK);
    }

    @PostMapping("/applicant")
    public Applicant createTutorial(@RequestBody Applicant applicant){
        Optional<Country> country=countryRepo.findById(applicant.country.getId());
        applicant.country = country.get();
        Optional<City> city=cityRepo.findById(applicant.city.getId());
        applicant.city = city.get();
        Optional<University> university=universityReppo.findById(applicant.university.getId());
        applicant.university = university.get();
        Optional<Department> department=departmentRepo.findById(applicant.department.getId());
        applicant.department = department.get();
        Optional<ExperienceYears> experienceYears=experienceYearsRepo.findById(applicant.experienceYears.getId());
        applicant.experienceYears = experienceYears.get();
        Optional<User> user=userRepo.findById(applicant.user.getId());
        applicant.user = user.get();
        return (Applicant) applicantRepo.save(applicant);
    }


    @PutMapping("/{applicantId}")
    public Applicant updateOneApplicant(@PathVariable Long applicantId, @RequestBody Applicant newApplicant){
        Optional<Applicant> applicant=applicantRepo.findById(applicantId);
        if (applicant.isPresent()) {
            Applicant foundApplicant = applicant.get();
            foundApplicant.setName(newApplicant.getName());
            foundApplicant.setSurname(newApplicant.getSurname());
            foundApplicant.setPhoneNumber(newApplicant.getPhoneNumber());
            foundApplicant.setEmail(newApplicant.getEmail());
            foundApplicant.setDateGraduation(newApplicant.getDateGraduation());
            foundApplicant.setCity(newApplicant.getCity());
            foundApplicant.setCountry(newApplicant.getCountry());
            foundApplicant.setDepartment(newApplicant.getDepartment());
            foundApplicant.setUniversity(newApplicant.getUniversity());
            foundApplicant.setExperienceYears(newApplicant.getExperienceYears());

          /*foundApplicant.setWorkingModel(newApplicant.getWorkingModel());
            foundApplicant.setLanguage(newApplicant.getLanguage());
            foundApplicant.setCompetence(newApplicant.getCompetence());*/
            applicantRepo.save(foundApplicant);
            return foundApplicant;
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Applicant> deleteApplicant(@PathVariable("id") Long id){
        Applicant applicant= applicantRepo.findById(id).orElseThrow(null);
        applicant.setIsActive(Long.valueOf("0"));
        applicantRepo.save(applicant);
        return ResponseEntity.ok(applicant);
    }

    @PostMapping("/multiSelect")
    public List<Applicant> multiSelect(@RequestParam(value = "language") String language,
                                       @RequestParam(value = "department") String department,
                                       @RequestParam(value = "experince") String experince){

        List<Applicant> dto;
        List<String> departmentList = new ArrayList<>();
        List<String> languageList = new ArrayList<>();
        List<String> experinceList = new ArrayList<>();
        try {
            language = StringUtils.isEmpty(language) ? "":language;
            department = StringUtils.isEmpty(department) ? "":department;
            experince = StringUtils.isEmpty(experince) ? "":experince;
            languageList = new ArrayList<String>(Arrays.asList(language.split(",")));
            departmentList = new ArrayList<String>(Arrays.asList(department.split(",")));
            experinceList = new ArrayList<String>(Arrays.asList(experince.split(",")));
        }catch (Exception e){
            System.out.println("Error message: "+e.getMessage());
        }
        dto = applicantRepo.findApplicantByMultiSelect(languageList,departmentList,experinceList);
        return dto;
    }

   /* @PutMapping("/applicants/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable("id") Long id, @RequestBody Applicant applicant) {
        Applicant _applicant = applicantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Applicant with id = " + id));

        _applicant.setName(applicant.getName());
        _applicant.setSurname(applicant.getSurname());
        _applicant.setEmail(applicant.getEmail());
        _applicant.setPhoneNumber(applicant.getPhoneNumber());

        return new ResponseEntity<>(applicantRepo.save(_applicant), HttpStatus.OK);
    }*/

}
