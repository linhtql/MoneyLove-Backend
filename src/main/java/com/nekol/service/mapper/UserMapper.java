package com.nekol.service.mapper;

import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setLoggedIn(entity.isLoggedIn());

        return dto;
    }

    public User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setLoggedIn(dto.isLoggedIn());
        entity.setId(dto.getId());

        return entity;
    }
}
