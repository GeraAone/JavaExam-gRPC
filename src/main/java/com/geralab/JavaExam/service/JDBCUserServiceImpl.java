package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mapper.UserMapper;
import com.geralab.JavaExam.repository.MyJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JDBCUserServiceImpl implements UserService {
    private final MyJdbcTemplate template;
    private final UserMapper mapper;

    private User createUser(ResultSet resultSet){
        try {
            String country = resultSet.getString("country");
            return new User(
                    resultSet.getString("firstName"),
                    resultSet.getInt("age"),
                    Country.valueOf(country));
        } catch(SQLException e) {
            throw new RuntimeException("creating user failed");
        }
    }

    public List<UserDto> getAllUsers(){
        try {
            return template.statement(stmt -> {
                ResultSet resultSet = stmt.executeQuery("select * from users");
                List<UserDto> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(mapper.toDto(createUser(resultSet)));
                }
                return result;
            });
        }
        catch(SQLException e) {
            throw new RuntimeException("getting all users failed");
        }
    }

    public List<UserDto> getAllUsersOlderThanRequestAge(Integer age){
        try {
            return template.preparedStatement("select * from users where user.age > ?", selectUser -> {
                List<UserDto> users = new ArrayList<>();
                selectUser.setInt(1, age);
                ResultSet resultSet = selectUser.executeQuery();
                while (resultSet.next()) {
                    users.add(mapper.toDto(createUser(resultSet)));
                }
                return users;
            });
        } catch(SQLException e) {
            throw new RuntimeException("getting all users under" + age + "failed");
        }
    }

    @Override
    public List<UserDto> getAllUsersOfCurrentAge(Integer age) {
        try {
            return template.preparedStatement("select * from users where user.age = ?", selectUser -> {
                List<UserDto> users = new ArrayList<>();
                selectUser.setInt(1, age);
                ResultSet resultSet = selectUser.executeQuery();
                while (resultSet.next()) {
                    users.add(mapper.toDto(createUser(resultSet)));
                }
                return users;
            });
        } catch(SQLException e) {
            throw new RuntimeException("getting all users under" + age + "failed");
        }
    }

    @Override
    public List<UserDto> getAllUsersFilteredByAgeAndCountry(Integer age, Country country) {
        return List.of();
    }

    public UserDto addUser(UserDto newUser) {
        try {
            template.preparedStatement("insert into users(first_name, age, country) values (?, ?, ?)", insertUser -> {
                insertUser.setString(1, newUser.getFirstName());
                insertUser.setInt(2, newUser.getAge());
                insertUser.setString(3, newUser.getCountry().toString());
                insertUser.execute();
            });
            return newUser;
        } catch(SQLException e) {
            throw new RuntimeException("adding user failed");
        }
    }

}
