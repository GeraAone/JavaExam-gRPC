package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.RegistrationRequestDto;
import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mapper.UserMapper;
import com.geralab.JavaExam.repository.RoleRepository;
import com.geralab.JavaExam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Primary
public class JpaUserServiceImpl implements UserService, UserDetailsService {
    public final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

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

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь с логином '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList()
        );
    }

    public User createNewUser (RegistrationRequestDto registrationRequestDto) {
        User user = new User();
        user.setUsername(registrationRequestDto.getUsername());
        user.setEmail(registrationRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByRoleName().get()));
        return userRepository.save(user);
    }
}
