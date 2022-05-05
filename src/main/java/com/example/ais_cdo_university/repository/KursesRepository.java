package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Kurs;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface KursesRepository extends CrudRepository<Kurs, Long> {
    Kurs findKursByStudent(StudentEntity studentEntity);

    Iterable<Kurs> findKursByKurs(Integer kurs);
}
