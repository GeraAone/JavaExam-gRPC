package com.geralab.JavaExam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateResponseDto {
    @JsonProperty("login")
    String username;
    @JsonProperty("jwt_token")
    String token;
}
