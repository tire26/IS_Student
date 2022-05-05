package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.DateStartEducation;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatesStartEducationRepository extends CrudRepository<DateStartEducation, Long> {

    DateStartEducation findByStudentEntity(StudentEntity studentEntity);
}
