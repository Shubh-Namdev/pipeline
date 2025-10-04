package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{message}")
    public User createUser(@PathVariable String message) {
        User user = new User();
        user.setMessage(message);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(User::getMessage)
                .orElse("Message not found");
    }
}
