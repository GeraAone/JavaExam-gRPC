package com.geralab.JavaExam.dto;

import com.geralab.JavaExam.entity.Country;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class UserDto {
    @NotNull
    private String firstName;

    @Min(value = 18)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;

    private OffsetDateTime time;
}
