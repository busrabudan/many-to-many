package com.example.demo.controller;

import com.example.demo.entity.Applicant;
import com.example.demo.entity.Language;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.ApplicantRepo;
import com.example.demo.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private ApplicantRepo applicantRepo;

    @GetMapping("/allLanguages")
    public List<Language> getAllLanguages(){
        return languageRepo.findAll();
    }

    @GetMapping("/applicants/{applicantId}/languages")
    public ResponseEntity<List<Language>> getAllLanguagesByApplicantId(@PathVariable(value = "applicantId") Long applicantId){
        if(!applicantRepo.existsById(applicantId)){
            throw new ResourceNotFoundException("Not found Tutorial with id = " + applicantId);
        }
        List<Language> languages=languageRepo.findLanguagesByApplicantsId(applicantId);
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    @GetMapping("/languages/{languageId}/applicants")
    public ResponseEntity<List<Applicant>> getAllApplicantsByLanguageId(@PathVariable(value = "languageId") Long languageId) {
        if (!languageRepo.existsById(languageId)) {
            throw new ResourceNotFoundException("Not found Language  with id = " + languageId);
        }

        List<Applicant> applicants = applicantRepo.findApplicantsByLanguagesId(languageId);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @GetMapping("/languages/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable(value = "id") Long id){
        Language language=languageRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found Tag with id = " + id));
        return new ResponseEntity<>(language,HttpStatus.OK);
    }



    @PostMapping("/applicants/{applicantId}/languages")
    public ResponseEntity<Language> addLanguage(@PathVariable(value = "applicantId") Long applicantId,
                                                @RequestBody Language languageRequest) {
        Language language = applicantRepo.findById(applicantId).map(tutorial -> {
            Long languageId = languageRequest.getId();
            System.out.println("applicantId: "+applicantId+ " "+"languageId: " + languageId);

            // tag is existed
            if (languageId != null && languageId != 0) {
                Language _Language = languageRepo.findById(languageId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Language with id = " + languageId));
                tutorial.addLanguagess(_Language);
                applicantRepo.save(tutorial);
                return _Language;
            }

            // add and create new Tag
            tutorial.addLanguagess(languageRequest);
            return languageRepo.save(languageRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Applicant with id = " + applicantId));

        return new ResponseEntity<>(language, HttpStatus.CREATED);
    }
    @PutMapping("/applicants/{applicantId}/languages/{languageId}")
    public ResponseEntity<HttpStatus> updateLanguageFromApplicant(@PathVariable (value = "applicantId") Long applicantId, 
                                                                  @PathVariable(value = "languageId") Long languageId, 
                                                                  @RequestBody Language languageRequest){
        Applicant applicant = applicantRepo.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Applicants with id = " + applicantId));
        
        applicant.removeLanguage(languageId);
        applicantRepo.save(applicant);

        Language language = applicantRepo.findById(applicantId).map(tutorial -> {

            // tag is existed
            if (languageId != null && languageId != 0) {
                Language _Language = languageRepo.findById(languageRequest.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Language with id = " + languageId));
                tutorial.addLanguagess(_Language);
                applicantRepo.save(tutorial);
                return _Language;
            }

            // add and create new Tag
            tutorial.addLanguagess(languageRequest);
            return languageRepo.save(languageRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Applicant with id = " + applicantId));

        return new ResponseEntity(language, HttpStatus.CREATED);
    }

    @PutMapping("/languages/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("id") Long id,@RequestBody Language languageRequest){
        Language language = languageRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("LanguageId " + id + "not found"));
        language.setName(languageRequest.getName());
        return new ResponseEntity<>(languageRepo.save(language), HttpStatus.OK);
    }

    @DeleteMapping("/applicants/{applicantId}/languages/{languageId}")
    public ResponseEntity<HttpStatus> deleteLanguageFromApplicant(@PathVariable(value = "applicantId") Long applicantId, @PathVariable(value = "languageId") Long languageId) {
        Applicant applicant = applicantRepo.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Applicant with id = " + applicantId));

        applicant.removeLanguage(languageId);
        applicantRepo.save(applicant);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
