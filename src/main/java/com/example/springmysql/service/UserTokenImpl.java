package com.example.springmysql.service;

import com.example.springmysql.dao.UserTokenDao;
import com.example.springmysql.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenImpl implements UserTokenService {
    @Autowired
    UserTokenDao userTokenDao;

    @Override
    public UserToken save(UserToken userToken) {
        return userTokenDao.save(userToken);
    }
}
