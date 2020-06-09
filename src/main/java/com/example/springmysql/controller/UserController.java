package com.example.springmysql.controller;

import com.example.springmysql.dao.UserDao;
import com.example.springmysql.dao.UserTokenDao;
import com.example.springmysql.model.User;
import com.example.springmysql.request.CreateUserRequest;
import com.example.springmysql.request.UpdateUserRequest;
import com.example.springmysql.service.LoginService;
import com.example.springmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User createUser = new User();
        createUser.setFname(createUserRequest.getFname());
        createUser.setLname(createUserRequest.getLname());
        createUser.setEmail(createUserRequest.getEmail());
        createUser.setGender(createUserRequest.getGender());
        createUser.setPassword(createUserRequest.getPassword());
        createUser.setCountry(createUserRequest.getCountry());
        createUser.setCity(createUserRequest.getCity());
        User newUser = userService.save(createUser);
        //  User newUser = userDao.save(createUser);
        return new ResponseEntity<Object>(newUser, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(@RequestParam(required = false) String gender, @RequestHeader("token") String token) {
        Boolean result = loginService.validateLoggedInUsers(token);
        if (result == true) {
            if (gender != null) {
                return userService.findByGender(gender);
                //return userDao.findByGender(gender);
            } else
                return userService.findAll();
            //return (List<User>) userDao.findAll();
        }
        return null;
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int id) {
        User existingUser = userService.findById(id);
        //User existingUser = userDao.findById(id);
        if (existingUser != null)
            return new ResponseEntity<Object>(existingUser, HttpStatus.OK);
        else
            return new ResponseEntity<Object>("", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateUser/{userid}")
    public ResponseEntity<Object> updateUsers(@PathVariable("userid") int id, @RequestBody UpdateUserRequest updateUserRequest) {
        User existingUser = userService.findById(id);
        //User existingUser = userDao.findById(id);

        if (existingUser == null) {
            return new ResponseEntity<Object>("", HttpStatus.NOT_FOUND);
        }

        if (updateUserRequest.getFname() != null) {
            existingUser.setFname(updateUserRequest.getFname());
        }

        if (updateUserRequest.getLname() != null) {
            existingUser.setLname(updateUserRequest.getLname());
        }
        userService.save(existingUser);
        //userDao.save(existingUser);
        return new ResponseEntity<Object>(existingUser, HttpStatus.OK);
    }

}

