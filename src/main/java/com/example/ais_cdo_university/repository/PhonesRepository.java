package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.Phone;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhonesRepository extends CrudRepository<Phone, Long> {
    Phone findPhoneByStudent(StudentEntity student);
}
