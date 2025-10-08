package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok().body("Hello, User!!!");
    }

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
