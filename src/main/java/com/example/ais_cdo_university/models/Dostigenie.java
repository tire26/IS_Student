package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "dostigenie")
public class Dostigenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Dostigenia student;

    @Column(name = "dostigenie", length = 200)
    private String dostigenie;

    @Column(name = "kollichestvo_balov")
    private Integer kollichestvoBalov;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dostigenia getStudent() {
        return student;
    }

    public void setStudent(Dostigenia student) {
        this.student = student;
    }

    public String getDostigenie() {
        return dostigenie;
    }

    public void setDostigenie(String dostigenie) {
        this.dostigenie = dostigenie;
    }

    public Integer getKollichestvoBalov() {
        return kollichestvoBalov;
    }

    public void setKollichestvoBalov(Integer kollichestvoBalov) {
        this.kollichestvoBalov = kollichestvoBalov;
    }

}