package com.example.ais_cdo_university.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dates_end_education")
public class DatesEndEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Column(name = "date_end_education")
    private LocalDate dateEndEducation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return studentEntity;
    }

    public void setStudent(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public LocalDate getDateEndEducation() {
        return dateEndEducation;
    }

    public void setDateEndEducation(LocalDate dateEndEducation) {
        this.dateEndEducation = dateEndEducation;
    }

}