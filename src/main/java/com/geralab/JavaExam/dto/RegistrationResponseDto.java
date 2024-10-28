package com.geralab.JavaExam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponseDto {
    private Long id;

    @JsonProperty("login")
    private String username;

    private String email;
}
