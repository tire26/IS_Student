package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "kurses")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "kurs")
    private Integer kurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Integer getKurs() {
        return kurs;
    }

    public void setKurs(Integer kurs) {
        this.kurs = kurs;
    }

}