package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "kollichestvo_balov_v_systeme_uspech")
public class KollichestvoBalovVSystemeUspech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "kollichestov_balov")
    private Integer kollichestovBalov;

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

    public Integer getKollichestovBalov() {
        return kollichestovBalov;
    }

    public void setKollichestovBalov(Integer kollichestovBalov) {
        this.kollichestovBalov = kollichestovBalov;
    }

}