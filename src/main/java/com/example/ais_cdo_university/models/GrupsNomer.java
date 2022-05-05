package com.example.ais_cdo_university.models;

import javax.persistence.*;

@Entity
@Table(name = "grups_nomer")
public class GrupsNomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "grup_nomer")
    private String grupNomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrupNomer() {
        return grupNomer;
    }

    public void setGrupNomer(String grupNomer) {
        this.grupNomer = grupNomer;
    }

}