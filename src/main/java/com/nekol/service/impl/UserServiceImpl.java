package com.nekol.service.impl;

import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.entity.User;
import com.nekol.domain.enumeration.AlertMessage;
import com.nekol.payload.request.RegisterRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.UserRepository;
import com.nekol.service.UserService;
import com.nekol.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public MessageResponse create(RegisterRequest request) {

        MessageResponse response = new MessageResponse(AlertMessage.INTERNAL_ERROR.getMessage());
        try {
            UserDTO newUserDTO = new UserDTO(request);
            if (userRepository.existsByUsername(request.getUsername())) {
                response = new MessageResponse(AlertMessage.REGISTER_FAIL.getMessage());
                return response;
            }

            userRepository.save(userMapper.toEntity(newUserDTO));
            response.setMessage(AlertMessage.REGISTER_SUCCESSFUL.getMessage());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.map(userMapper::toDTO).orElse(null);
    }
}
