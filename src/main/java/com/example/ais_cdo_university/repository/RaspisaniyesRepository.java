package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.GrupsNomer;
import com.example.ais_cdo_university.models.RaspisaniyeOfGroup;
import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaspisaniyesRepository extends CrudRepository<RaspisaniyeOfGroup, Long> {
    RaspisaniyeOfGroup findRaspisaniyeByStudent(StudentEntity student);

    Iterable<RaspisaniyeOfGroup> findRaspisaniyeOfGroupsByGrupsNomer(GrupsNomer grupsNomer);
}
