package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "dop_specialnosti")
public class DopSpecialnosti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "dop_specialnost", length = 200)
    private String dopSpecialnost;

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

    public String getDopSpecialnost() {
        return dopSpecialnost;
    }

    public void setDopSpecialnost(String dopSpecialnost) {
        this.dopSpecialnost = dopSpecialnost;
    }

}