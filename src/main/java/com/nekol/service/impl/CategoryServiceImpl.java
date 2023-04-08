package com.nekol.service.impl;

import com.nekol.config.JwtAuthorizationFilter;
import com.nekol.config.JwtUtils;
import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.enumeration.AlertMessage;
import com.nekol.payload.request.CreateCategoryRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.CategoryRepository;
import com.nekol.repository.UserRepository;
import com.nekol.service.AuthService;
import com.nekol.service.CategoryService;
import com.nekol.service.UserService;
import com.nekol.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;
    @Override
    public MessageResponse create(CreateCategoryRequest request) {

        MessageResponse response = new MessageResponse(AlertMessage.INTERNAL_ERROR.getMessage());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
//        UserDTO userDTO = userRepository.findByUsername(currentUserName);
        try {
            CategoryDTO categoryDTO = new CategoryDTO(request);
            categoryDTO.setUserDTO(new UserDTO());
            if(categoryRepository.existsByName(categoryDTO.getName())) {
                response = new MessageResponse(AlertMessage.REGISTER_FAIL.getMessage());
                return response;
            }
            categoryRepository.save(categoryMapper.toEntity(categoryDTO));
        } catch (Exception e) {
            response.setMessage(e.getMessage()) ;
        }
        return response;
    }
}
