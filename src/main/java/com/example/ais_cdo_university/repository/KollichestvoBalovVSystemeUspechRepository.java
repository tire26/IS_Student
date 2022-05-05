package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.KollichestvoBalovVSystemeUspech;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface KollichestvoBalovVSystemeUspechRepository extends CrudRepository<KollichestvoBalovVSystemeUspech, Long> {
    KollichestvoBalovVSystemeUspech findKollichestvoBalovVSystemeUspechByStudent(StudentEntity student);
}
