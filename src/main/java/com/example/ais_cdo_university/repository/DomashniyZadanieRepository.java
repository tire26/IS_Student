package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.DomashniyZadanie;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomashniyZadanieRepository extends CrudRepository<DomashniyZadanie, Long> {

    DomashniyZadanie findByStudentEntity(StudentEntity studentEntity);
}
