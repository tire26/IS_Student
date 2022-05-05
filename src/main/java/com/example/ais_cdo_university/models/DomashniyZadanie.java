package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "domashniy_zadanie")
public class DomashniyZadanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Column(name = "domashniy_zadanie", length = 1000)
    private String domashniyZadanie;

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

    public String getDomashniyZadanie() {
        return domashniyZadanie;
    }

    public void setDomashniyZadanie(String domashniyZadanie) {
        this.domashniyZadanie = domashniyZadanie;
    }

}