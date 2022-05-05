package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Zadolgennost;
import com.example.ais_cdo_university.models.ZadolgenoostiStudenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZadolgennostRepository extends CrudRepository<Zadolgennost, Long> {
    Iterable<Zadolgennost> findZadolgennostsByStudent(ZadolgenoostiStudenta zadolgenoostiStudenta);
}
