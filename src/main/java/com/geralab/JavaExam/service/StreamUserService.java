package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mapper.UserMapper;
import com.geralab.JavaExam.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamUserService implements UserService{

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Transactional
    public List<UserDto> getAllUsersOlderThanRequestAge(Integer age) {
//        return userRepository.findAll().stream().filter((user ->
//            user.getAge() > age)).toList();
        return null;
    }

    @Transactional
    public List<UserDto> getAllUsersOfCurrentAge(Integer age) {
//        return toMapGroupingByAge().get(age);
        return null;
    }

    @Transactional
    public List<UserDto> getAllUsersFilteredByAgeAndCountry(Integer age, Country country) {
//        return userRepository.findAll().stream().filter((user ->
//                user.getAge() > age && user.getCountry().equals(country))).toList();
        return null;
    }

    public Map<Integer, List<User>> toMapGroupingByAge() {
        return userRepository.findAll().stream().collect(Collectors.groupingBy(User::getAge));
    }


    @Transactional
    public UserDto addUser(UserDto newUser) {
//        try{
//            User user = userRepository.save(newUser);
//            return user;
//        }
//        catch(RuntimeException e) {
//            throw new RuntimeException("User cannot be saved!");
//        }
        return null;
    }
}
