package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.DopSpecialnosti;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DopSpecialnostiRepository extends CrudRepository<DopSpecialnosti, Long> {
    DopSpecialnosti findDopSpecialnostiByStudent(StudentEntity studentEntity);
}
