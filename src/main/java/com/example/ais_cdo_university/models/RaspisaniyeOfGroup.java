package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "raspisaniyes_of_group")
public class RaspisaniyeOfGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public GrupsNomer getGrupsNomer() {
        return grupsNomer;
    }

    public void setGrupsNomer(GrupsNomer grupsNomer) {
        this.grupsNomer = grupsNomer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GrupsNomer grupsNomer;

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    public String getRaspisanine() {
        return raspisanine;
    }

    public void setRaspisanine(String raspisanine) {
        this.raspisanine = raspisanine;
    }

    @Column(name = "raspisanie")
    private String raspisanine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}