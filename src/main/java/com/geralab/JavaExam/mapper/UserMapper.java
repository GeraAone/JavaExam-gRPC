package com.geralab.JavaExam.mapper;

import com.geralab.JavaExam.dto.UserDto;
import com.geralab.JavaExam.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto dto) {
        return User.builder()
                .age(dto.getAge())
                .firstName(dto.getFirstName())
                .country(dto.getCountry())
                .build();
    }

    public UserDto toDto(User entity) {
        return UserDto.builder()
                .age(entity.getAge())
                .firstName(entity.getFirstName())
                .country(entity.getCountry())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
