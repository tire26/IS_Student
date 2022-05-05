package com.example.ais_cdo_university.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dates_start_education")
public class DateStartEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Column(name = "date")
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}