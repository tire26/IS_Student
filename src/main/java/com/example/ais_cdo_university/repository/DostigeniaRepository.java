package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Dostigenia;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DostigeniaRepository extends CrudRepository<Dostigenia, Long> {
    Dostigenia findDostigeniaByStudent(StudentEntity student);
}
