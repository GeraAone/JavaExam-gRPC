package com.geralab.JavaExam.controller;

import com.geralab.JavaExam.dto.AuthenticateRequestDto;
import com.geralab.JavaExam.dto.AuthenticateResponseDto;
import com.geralab.JavaExam.dto.RegistrationRequestDto;
import com.geralab.JavaExam.dto.RegistrationResponseDto;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.exception.ApiError;
import com.geralab.JavaExam.security.utils.TokenUtils;
import com.geralab.JavaExam.service.AuthService;
import com.geralab.JavaExam.service.JpaUserServiceImpl;
import com.geralab.JavaExam.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


//!!! вынести бизнес-логику в AuthService

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    private final RoleService roleService;
    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<AuthenticateResponseDto> authenticate(@RequestBody AuthenticateRequestDto requestDto) {
        return ResponseEntity.ok(authService.authenticate(requestDto));
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDto> createNewUser(@RequestBody @Valid RegistrationRequestDto registrationRequestDto) {
        return ResponseEntity.ok(authService.createNewUser(registrationRequestDto));
    }

    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public String getUserInfo(Principal principal) {
        return principal.getName();
    }

    @GetMapping("user-api/v1/admin")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
