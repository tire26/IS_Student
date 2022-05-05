package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "zadolgennost")
public class Zadolgennost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private ZadolgenoostiStudenta student;

    @Column(name = "zadolgennost_name")
    private String zadolgennostName;

    public ZadolgenoostiStudenta getStudent() {
        return student;
    }

    public void setStudent(ZadolgenoostiStudenta student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZadolgennostName() {
        return zadolgennostName;
    }

    public void setZadolgennostName(String zadolgennostName) {
        this.zadolgennostName = zadolgennostName;
    }

}