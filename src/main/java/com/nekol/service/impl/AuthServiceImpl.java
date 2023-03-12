package com.nekol.service.impl;

import com.nekol.config.JwtUtils;
import com.nekol.domain.dto.UserDTO;
import com.nekol.payload.request.LoginRequest;
import com.nekol.payload.request.RegisterRequest;
import com.nekol.payload.response.JwtResponse;
import com.nekol.payload.response.MessageResponse;
import com.nekol.service.AuthService;
import com.nekol.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final String BEARER = "Bearer";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Override
    public JwtResponse authenticateUser(LoginRequest request) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();

        return new JwtResponse(
                jwtUtils.generateToken(userDTO),
                BEARER,
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail());
    }

    @Override
    public MessageResponse registerUser(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        return userService.create(request);
    }
}
