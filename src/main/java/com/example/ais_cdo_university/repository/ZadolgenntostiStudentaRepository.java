package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.StudentEntity;
import com.example.ais_cdo_university.models.ZadolgenoostiStudenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZadolgenntostiStudentaRepository extends CrudRepository<ZadolgenoostiStudenta, Long> {
    ZadolgenoostiStudenta findZadolgenoostiStudentaByStudent(StudentEntity student);
}
