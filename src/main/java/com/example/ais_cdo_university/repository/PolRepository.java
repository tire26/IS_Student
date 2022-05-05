package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Pol;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PolRepository extends CrudRepository<Pol, Long> {
    Pol findPolByStudent(StudentEntity student);
}
