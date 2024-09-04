package com.geralab.JavaExam.controller;

import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-api/v1")
public class UserController {
    public final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        return this.userService.addUser(newUser);
    }

    @GetMapping("/additional-info")
    public List<User> getAllUsersOlderThanRequestAge(@RequestParam(name = "age", required = false) Integer age) {
        return this.userService.getAllUsersOlderThanRequestAge(age);
    }
}
