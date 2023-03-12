package com.nekol.service.mapper;

import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Component
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);

    default User defaultBehavior() {
        return new User();
    }
}
