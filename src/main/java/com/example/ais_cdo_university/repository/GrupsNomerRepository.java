package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.GrupsNomer;
import org.springframework.data.repository.CrudRepository;

public interface GrupsNomerRepository extends CrudRepository<GrupsNomer, Long> {
    GrupsNomer findGrupsNomersByGrupNomer(String grupsNomer);
}
