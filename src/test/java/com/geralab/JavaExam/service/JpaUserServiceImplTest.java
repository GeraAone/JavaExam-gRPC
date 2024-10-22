package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mapper.UserMapper;
import com.geralab.JavaExam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JpaUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private JpaUserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Тест на успешное выполнение метода getAllUsers()
    @Test
    void testGetAllUsersSuccess() {
        // Mock данные
        User user = new User();
        UserDto userDto = new UserDto();
        List<User> users = List.of(user);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    // Тест на пустой результат метода getAllUsers()
    @Test
    void testGetAllUsersThrowsNoSuchElementException() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getAllUsers();
        });

        assertEquals("Users cannot be found!", exception.getMessage());
    }

    // Тест на успешное выполнение метода getAllUsersOlderThanRequestAge()
    @Test
    void testGetAllUsersOlderThanRequestAgeSuccess() {
        User user = new User();
        UserDto userDto = new UserDto();
        List<User> users = List.of(user);

        when(userRepository.findAllByAgeIsGreaterThanEqual(anyInt())).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsersOlderThanRequestAge(30);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAllByAgeIsGreaterThanEqual(30);
    }

    // Тест на отсутствие пользователей в getAllUsersOlderThanRequestAge
    @Test
    void testGetAllUsersOlderThanRequestAgeThrowsException() {
        when(userRepository.findAllByAgeIsGreaterThanEqual(anyInt())).thenReturn(Collections.emptyList());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getAllUsersOlderThanRequestAge(30);
        });

        assertEquals("Users cannot be found!", exception.getMessage());
    }

    // Тест на успешное выполнение метода getAllUsersOfCurrentAge()
    @Test
    void testGetAllUsersOfCurrentAgeSuccess() {
        User user = new User();
        UserDto userDto = new UserDto();
        List<User> users = List.of(user);

        when(userRepository.findUsersByAge(anyInt())).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsersOfCurrentAge(25);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findUsersByAge(25);
    }

    // Тест на отсутствие пользователей в getAllUsersOfCurrentAge
    @Test
    void testGetAllUsersOfCurrentAgeThrowsException() {
        when(userRepository.findUsersByAge(anyInt())).thenReturn(Collections.emptyList());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getAllUsersOfCurrentAge(25);
        });

        assertEquals("Users cannot be found!", exception.getMessage());
    }

    // Тест на успешное выполнение метода getAllUsersFilteredByAgeAndCountry()
    @Test
    void testGetAllUsersFilteredByAgeAndCountrySuccess() {
        User user = new User();
        UserDto userDto = new UserDto();
        List<User> users = List.of(user);

        when(userRepository.findUserFilteredByAgeAndCountry(anyInt(), anyString())).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsersFilteredByAgeAndCountry(30, Country.RUSSIA);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findUserFilteredByAgeAndCountry(30, "RUSSIA");
    }

    // Тест на отсутствие пользователей в getAllUsersFilteredByAgeAndCountry
    @Test
    void testGetAllUsersFilteredByAgeAndCountryThrowsException() {
        when(userRepository.findUserFilteredByAgeAndCountry(anyInt(), anyString())).thenReturn(Collections.emptyList());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getAllUsersFilteredByAgeAndCountry(30, Country.RUSSIA);
        });

        assertEquals("Users cannot be found!", exception.getMessage());
    }

    // Тест на успешное добавление пользователя в addUser()
    @Test
    void testAddUserSuccess() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto result = userService.addUser(userDto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    // Тест на ошибку при добавлении пользователя в addUser()
    @Test
    void testAddUserThrowsException() {
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenThrow(new IllegalArgumentException());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(new UserDto());
        });

        assertEquals("User cannot be saved!", exception.getMessage());
    }

}