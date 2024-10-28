package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;

import java.util.List;

public interface UserService{
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsersOlderThanRequestAge(Integer age);
    List<UserDto> getAllUsersOfCurrentAge(Integer age);
    List<UserDto> getAllUsersFilteredByAgeAndCountry(Integer age, Country country);
    UserDto addUser(UserDto newUser);
}
