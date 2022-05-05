package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends CrudRepository<StudentEntity, Long> {
    StudentEntity findStudentByUser_id(Long user_id);
    StudentEntity findStudentById(Long id);

    @Query("""
            select k.kurs, ds.dopSpecialnost, f.fio, p.pol, fakult.fakultet from Kurs k, DopSpecialnosti ds, Fio f, Pol p, Fakultet fakult
            where (:fio is null or f.fio = :fio)
            and (:pol is null or p.pol = :pol)
            and (:fakultet is null or fakult.fakultet = :fakultet)
            and (:kurs is null or k.kurs = :kurs)
            and (:dopSpecialnost is null or ds.dopSpecialnost = :dopSpecialnost)""")
    Iterable<StudentEntity> findByStudentEntity(@Param("fio") String fio, @Param("pol") String pol, @Param("fakultet") String fakultet, @Param("kurs") Integer kurs, @Param("dopSpecialnost") String dopSpecialnost);

    Iterable<StudentEntity> findStudentEntitiesByGrupNomer(String grupNomer);
}
