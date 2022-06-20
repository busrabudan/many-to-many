package com.example.demo.entity;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false, length = 40)
    private String name;

    @Column( nullable = false,  length = 40)
    private String surname;

    @Column( nullable = false, unique = true, length = 45)
    private String phoneNumber;

    @Column( nullable = false, unique = true, length = 45)
    private String email;

    @Column( nullable = false, length = 40)
    private String dateGraduation;

    @Column(nullable = false)
    private Long isActive;

        @ManyToMany(fetch = FetchType.EAGER,
                cascade = {
                        CascadeType.MERGE
                })
        @JoinTable(name = "applicant_language", joinColumns = {
                @JoinColumn(name = "applicant_id", referencedColumnName = "id")
        },inverseJoinColumns = {
                @JoinColumn(name = "language_id", referencedColumnName = "id")
        })
        private Set<Language> languages = new HashSet<Language>();

    @ManyToOne
    public User user;

    @ManyToOne
    public Country country;

    @ManyToOne
    public City city;

    @ManyToOne
    public University university;

    @ManyToOne
    public Department department;

    @ManyToOne
    public ExperienceYears experienceYears;

    public Applicant(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDateGraduation() {
        return dateGraduation;
    }

    public Long getIsActive() {
        return isActive;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public User getUser() {
        return user;
    }

    public Country getCountry() {
        return country;
    }

    public City getCity() {
        return city;
    }

    public University getUniversity() {
        return university;
    }

    public Department getDepartment() {
        return department;
    }

    public ExperienceYears getExperienceYears() {
        return experienceYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateGraduation(String dateGraduation) {
        this.dateGraduation = dateGraduation;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setExperienceYears(ExperienceYears experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Applicant(String name, String surname, String phoneNumber, String email, String dateGraduation, Long isActive) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateGraduation = dateGraduation;
        this.isActive = isActive;
    }

    public void addLanguagess(Language language){
        this.languages.add(language);
        language.getApplicants().add(this);
    }
    public void removeLanguage(Long languageId){
        Language language = this.languages.stream().filter(l->l.getId() == languageId).findFirst().orElse(null);
        if (language != null){
            this.languages.remove(language);
            language.getApplicants().remove(this);
        }
    }

    public Applicant(String name, String surname, String phoneNumber, String email){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Applicant [id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber + "]";
    }
}
