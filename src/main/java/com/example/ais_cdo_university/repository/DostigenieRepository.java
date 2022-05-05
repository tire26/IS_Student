package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Dostigenia;
import com.example.ais_cdo_university.models.Dostigenie;
import org.springframework.data.repository.CrudRepository;

public interface DostigenieRepository extends CrudRepository<Dostigenie, Long> {
    Iterable<Dostigenie> findDostigeniesByStudent(Dostigenia dostigenia);
}
