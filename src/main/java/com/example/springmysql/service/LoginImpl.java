package com.example.springmysql.service;


import com.example.springmysql.dao.UserDao;
import com.example.springmysql.dao.UserTokenDao;
import com.example.springmysql.model.User;
import com.example.springmysql.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
public class LoginImpl implements LoginService {
    @Autowired
    UserTokenDao userTokenDao;
    @Autowired
    UserDao userDao;


    @Override
    public Boolean validateLoggedInUsers(String token) {
        UserToken userToken = userTokenDao.findByToken(token);
        Calendar createdDate = userToken.getCreated_date();
        Calendar currentTime = Calendar.getInstance();
        createdDate.add(Calendar.MINUTE, 2);

        if (currentTime.before(createdDate)) {
            return true;
        }
        return false;
    }
    @Override
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }
}
