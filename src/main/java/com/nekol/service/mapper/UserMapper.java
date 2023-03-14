package com.nekol.service.mapper;

import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    public User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
