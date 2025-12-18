package com.smartcanteen.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcanteen.backend.model.User;
import com.smartcanteen.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User existingUser = userRepository.findByRegisterNo(user.getRegisterNo());
        if (existingUser != null) {
            return "User already exists";
        }
        userRepository.save(user);
        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByRegisterNo(user.getRegisterNo());
        if (existingUser == null) {
            return "User not found";
        }
        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }
        return "Login successful";
    }
}
