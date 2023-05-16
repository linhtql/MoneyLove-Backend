package com.nekol.utils;

import com.nekol.domain.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static UserDTO getUserCurrenUtil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();

        return userDTO;
    }
}
