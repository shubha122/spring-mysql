package com.example.springmysql.service;

import com.example.springmysql.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findByGender(String gender);

    List<User> findAll();

    User findById(int id);
}
