package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.DatesEndEducation;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatesEndEducationRepository extends CrudRepository<DatesEndEducation, Long> {

    DatesEndEducation findByStudentEntity(StudentEntity studentEntity);
}
