package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.service.Impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController("/User")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/User/keyword/{keyword}")
    public ResponseEntity<List<User>> getUsersByKeyword(@PathVariable String keyword) {
        List<User> users = userService.getUsersByKeyword(keyword);
        if (users == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/User/pageSize/{pageSize}")
    public ResponseEntity<Integer> getPageNumber(@PathVariable int pageSize) {
        int pageNumber = userService.getPageNumber(pageSize);
        return new ResponseEntity<>(pageNumber, HttpStatus.OK);
    }

    @GetMapping("/User/getUsers/{pageNumber}/{pageSize}")
    public ResponseEntity<List<User>> getUsers(@PathVariable(name = "pageNumber") int pageNumber, @PathVariable(name = "pageSize") int pageSize) {
        List<User> users = userService.getUsers(pageNumber, pageSize);
        if (users == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/User")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
        if (user1.getId() == 0)
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @PutMapping("/User")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        if (user1.getAccount().getUsername() == "already exist")
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @DeleteMapping("/User/{userID}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userID) {
        userService.deleteUser(userID);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}