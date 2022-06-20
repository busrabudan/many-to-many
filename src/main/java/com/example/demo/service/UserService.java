package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User saveUser(User newUser) {
        return userRepo.save(newUser);
    }

    public User login(String name, String password) {
        User user=userRepo.findByNameAndPassword(name,password);
        return user;
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
