package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mapper.UserMapper;
import com.geralab.JavaExam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Primary
public class JpaUserServiceImpl implements UserService{
    public final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(){
        try{
            List<UserDto> list = new ArrayList<>();
            List<User> userList = userRepository.findAll();
            if(userList.isEmpty()) {
                throw new NoSuchElementException();
            }
            userList.forEach(user ->
                list.add(userMapper.toDto(user)));
            return list;
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("Users cannot be found!");
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOlderThanRequestAge(Integer age){
        try{
            List<UserDto> list = new ArrayList<>();
            List<User> userList = userRepository.findAllByAgeIsGreaterThanEqual(age);
            if(userList.isEmpty()) {
                throw new NoSuchElementException();
            }
            userList.forEach(user ->
                list.add(userMapper.toDto(user)));
            return list;
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("Users cannot be found!");
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOfCurrentAge(Integer age) {
        try{
            List<UserDto> list = new ArrayList<>();
            List<User> userList = userRepository.findUsersByAge(age);
            if(userList.isEmpty()) {
                throw new NoSuchElementException();
            }
            userList.forEach(user ->
                list.add(userMapper.toDto(user)));
            return list;
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("Users cannot be found!");
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersFilteredByAgeAndCountry(Integer age, Country country){
        try{
            List<UserDto> list = new ArrayList<>();
            String stringCountry = country.toString();
            List<User> userList = userRepository.findUserFilteredByAgeAndCountry(age, stringCountry);
            if(userList.isEmpty()) {
                throw new NoSuchElementException();
            }
            userList.forEach(user ->
                list.add(userMapper.toDto(user)));
            return list;
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("Users cannot be found!");
        }
    }

    @Transactional
    public UserDto addUser(UserDto newUser) {
        try{
            User user = userRepository.save(userMapper.toEntity(newUser));
            return userMapper.toDto(user);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("User cannot be saved!");
        }
    }

}
