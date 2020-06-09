package com.example.springmysql.dao;

import com.example.springmysql.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {

    List<User> findByGender(String gender);

    User findById(int id);

    User findByEmail(String email);
}
