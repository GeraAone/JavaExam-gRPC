package com.geralab.JavaExam.service;

import com.geralab.JavaExam.dto.AuthenticateRequestDto;
import com.geralab.JavaExam.dto.AuthenticateResponseDto;
import com.geralab.JavaExam.dto.RegistrationRequestDto;
import com.geralab.JavaExam.dto.RegistrationResponseDto;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.exception.UserAlreadyExistsException;
import com.geralab.JavaExam.security.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JpaUserServiceImpl userService;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public RegistrationResponseDto createNewUser(RegistrationRequestDto registrationRequestDto) {
        if (userService.findByUsername(registrationRequestDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Bad Request");
        }
        User user = userService.createNewUser(registrationRequestDto);
        return new RegistrationResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public AuthenticateResponseDto authenticate(AuthenticateRequestDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Access Denied");
        }

        UserDetails details = userService.loadUserByUsername(requestDto.getUsername());
        String jwt = tokenUtils.generateToken(details);
        return new AuthenticateResponseDto(details.getUsername(), jwt);
    }
}
