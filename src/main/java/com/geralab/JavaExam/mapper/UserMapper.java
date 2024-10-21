package com.geralab.JavaExam.mapper;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto dto) {
        return new User(
                dto.getFirstName(),
                dto.getAge(),
                dto.getCountry());
    }

    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getFirstName(),
                entity.getAge(),
                entity.getCountry(),
                entity.getCreatedAt());
    }
}
