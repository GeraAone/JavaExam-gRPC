package com.geralab.JavaExam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequestDto {
    @NotNull
    @JsonProperty("login")
    private String username;

    @NotNull
    @JsonProperty("password")
    private String password;

    private String email;

}
