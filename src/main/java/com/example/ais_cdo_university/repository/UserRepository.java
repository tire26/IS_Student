package com.example.ais_cdo_university.repository;

import com.example.ais_cdo_university.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);

    User findUserByLoginAndPassword(String login, String password);
}
