package com.geralab.JavaExam.service;

import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public List<User> getAllUsers(){
        try{
            return userRepository.findAll();
        }
        catch (NoSuchElementException e) {
            throw new RuntimeException("Users cannot be found!");
        }
    }

    public List<User> getAllUsersOlderThanRequestAge(Integer age){
        try{
            return userRepository.findAllByAgeIsGreaterThanEqual(age);
        }
        catch (NoSuchElementException e) {
            throw new RuntimeException("Users cannot be found!");
        }
    }

    public User addUser(User newUser) {
        try{
            User user = userRepository.save(newUser);
            return user;
        }
        catch(RuntimeException e) {
            throw new RuntimeException("User cannot be saved!");
        }
    }

}
