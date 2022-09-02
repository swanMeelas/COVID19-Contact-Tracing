package com.blockchain.data.anonymization.controller;

import com.blockchain.data.anonymization.Repository.UserRepository;
import com.blockchain.data.anonymization.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/registerme")
    public String processRegister(@RequestBody User user) {
        String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedPassword);
        userRepo.save(user);

        return "register_success";
    }
}
