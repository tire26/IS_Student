package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Fakultet;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface FakultetsRepository extends CrudRepository<Fakultet, Long> {
    Fakultet findFakultetByStudent(StudentEntity student);
}
