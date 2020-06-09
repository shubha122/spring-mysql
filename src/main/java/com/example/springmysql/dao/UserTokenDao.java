package com.example.springmysql.dao;

import com.example.springmysql.model.UserToken;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserTokenDao extends CrudRepository<UserToken, Integer> {
    UserToken findByToken(String token);
}
