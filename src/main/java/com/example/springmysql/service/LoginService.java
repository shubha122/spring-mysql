package com.example.springmysql.service;

import com.example.springmysql.model.User;

public interface LoginService {
    Boolean validateLoggedInUsers(String token);
    User findByEmail(String email);
}
