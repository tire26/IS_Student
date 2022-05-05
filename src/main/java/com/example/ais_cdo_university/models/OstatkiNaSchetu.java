package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "ostatki_na_schetu")
public class OstatkiNaSchetu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "ostatok_na_schetu")
    private Long ostatokNaSchetu;

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

    public Long getOstatokNaSchetu() {
        return ostatokNaSchetu;
    }

    public void setOstatokNaSchetu(Long ostatokNaSchetu) {
        this.ostatokNaSchetu = ostatokNaSchetu;
    }

}