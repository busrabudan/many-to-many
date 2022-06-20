package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User newUser){
        return userService.saveUser(newUser);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        User responce=userService.login(user.getName(),user.getPassword());
        if (responce!=null){
            return responce;
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/allUser")
    public List<User> findAllUser(){
        return userService.getAllUser();
    }
}
