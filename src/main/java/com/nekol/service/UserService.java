package com.nekol.service;

import com.nekol.domain.dto.UserDTO;
import com.nekol.payload.request.RegisterRequest;
import com.nekol.payload.response.MessageResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    MessageResponse create(RegisterRequest request);
    UserDTO update(UserDTO userDTO);
    UserDTO retrieveById(Long id);
}
