package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.payLoad.LoginRequest;
import com.example.demo.payLoad.Message;
import com.example.demo.payLoad.dto.AuthDTO;
import com.example.demo.payLoad.dto.UserDTO;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean authenticateUser(@RequestBody LoginRequest loginRequest) {
        Account account = accountRepository.getAccountByUsername(loginRequest.getUsername());
        return account.getPassword().equalsIgnoreCase(account.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<Message> registryUser(@RequestBody UserDTO userDTO) {
        if (userRepository.existsByName(userDTO.getUsername())) {
            return ResponseEntity.badRequest().body(new Message("Error: Username is already taken!"));
        } else {
            User user = new User();
            Account account = new Account();
            account.setUsername(userDTO.getUsername());
            account.setPassword(userDTO.getPassword());
            user.setAccount(account);
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setDateOfBirth(LocalDate.parse(userDTO.getDateOfBirth()));
            user.setGender(userDTO.getGender());
            userRepository.save(user);
            return ResponseEntity.ok(new Message("Success: User registered successfully!"));
        }
    }
}
