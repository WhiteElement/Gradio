package com.manu.GradeR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GradeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String testName;
    private String testDescription;
    private Float weighting;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private GradeTestType gradeType;

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "gradeTest")
    private List<Grade> grades = new ArrayList<>();

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    private SchoolClass schoolClass;


    public GradeTest(Long id, String testName, String testDescription, GradeTestType gradeType, List<Grade> grades) {
        Id = id;
        this.testName = testName;
        this.testDescription = testDescription;
        this.gradeType = gradeType;
        this.grades = grades;
    }

    public GradeTest(){}

    public Long getId() {
        return Id;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public GradeTestType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeTestType gradeType) {
        this.gradeType = gradeType;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "GradeTest{" +
                "Id=" + Id +
                ", testName='" + testName + '\'' +
                ", testDescription='" + testDescription + '\'' +
                ", gradeType=" + gradeType +
                ", grades=" + grades +
                ", schoolClass=" + schoolClass +
                '}';
    }

    public void assignToSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Float getWeighting() {
        return weighting;
    }

    public void setWeighting(Float weighting) {
        this.weighting = weighting;
    }
}
