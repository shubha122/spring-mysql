package com.example.springmysql.dao;

import com.example.springmysql.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Integer> {

    List<User> findByGender(String gender);
    User findById(int id);
}