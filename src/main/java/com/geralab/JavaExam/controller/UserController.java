package com.geralab.JavaExam.controller;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.exception.ApiError;
import com.geralab.JavaExam.service.JpaUserServiceImpl;
import com.geralab.JavaExam.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-api/v1")
public class UserController {
    public final UserService userService;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public List<UserDto> getUserByAge(@PathVariable(name = "id") Integer age) {
        return userService.getAllUsersOfCurrentAge(age);
    }

    @GetMapping("/country")
    public List<UserDto> getAllUsersFilteredByAgeAndCountry(@RequestParam(name = "age", required = false) Integer age, @RequestParam(name = "country", required = false) Country country) {
        return this.userService.getAllUsersFilteredByAgeAndCountry(age, country);
    }

    @PostMapping("/users")
    public UserDto addUser(@Valid @RequestBody UserDto newUser) {
        return this.userService.addUser(newUser);
    }

    @GetMapping("/additional-info")
    public List<UserDto> getAllUsersOlderThanRequestAge(@RequestParam(name = "age", required = false) Integer age) {
        return this.userService.getAllUsersOlderThanRequestAge(age);
    }
}
