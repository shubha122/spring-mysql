package com.example.springmysql.service;

import com.example.springmysql.dao.UserDao;
import com.example.springmysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findByGender(String gender) {
        return userDao.findByGender(gender);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

}
