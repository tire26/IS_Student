package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.TypeOfUser;
import com.example.ais_cdo_university.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesOfUsersRepository extends CrudRepository<TypeOfUser, Long> {

    TypeOfUser findByUser(User user);
}
