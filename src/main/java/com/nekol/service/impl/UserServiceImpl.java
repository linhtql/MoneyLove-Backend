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

import javax.transaction.Transactional;
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
    public UserDTO update(UserDTO userDTO) {
        log.debug("USER SERVICE update");
        User user = userMapper.toEntity(userDTO);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO retrieveById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.map(userMapper::toDTO).orElse(null);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("USER SERVICE loadUserByUsername");

        Optional<User> userOptional = userRepository.findByUsername(username);
////
        return userOptional.map(userMapper::toDTO).orElse(null);


    }
}

