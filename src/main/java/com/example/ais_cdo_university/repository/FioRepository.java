package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Fio;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FioRepository extends CrudRepository<Fio, Long> {
    Fio findFioByStudent(StudentEntity student);
}
