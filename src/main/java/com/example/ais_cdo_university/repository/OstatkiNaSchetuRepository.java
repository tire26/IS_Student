package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Fakultet;
import com.example.ais_cdo_university.models.OstatkiNaSchetu;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface OstatkiNaSchetuRepository extends CrudRepository<OstatkiNaSchetu, Long> {
    OstatkiNaSchetu findOstatkiNaSchetuByStudent(StudentEntity student);
}
