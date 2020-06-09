package com.example.springmysql.controller;

import com.example.springmysql.dao.UserDao;
import com.example.springmysql.dao.UserTokenDao;
import com.example.springmysql.model.User;
import com.example.springmysql.model.UserToken;
import com.example.springmysql.request.LoginRequest;
import com.example.springmysql.service.LoginService;
import com.example.springmysql.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Calendar;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
User loginUser = loginService.findByEmail(email);
        //User loginUser = userDao.findByEmail(email);

        if (loginUser == null) {
            return "User is not registered";
        } else if (!password.equals(loginUser.getPassword())) {
            return "password is incorrect";
        }
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        Calendar currentTime = Calendar.getInstance();

        // user token save
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUser(loginUser);
        userToken.setCreated_date(currentTime);
        userTokenService.save(userToken);
        //userTokenDao.save(userToken);
        return token;
    }
}