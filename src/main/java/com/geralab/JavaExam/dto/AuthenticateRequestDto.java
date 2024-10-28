package com.geralab.JavaExam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthenticateRequestDto {
    @JsonProperty("login")
    String username;
    @JsonProperty("password")
    String password;
}
