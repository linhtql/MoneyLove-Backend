package com.nekol.service.mapper;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    @Autowired
    private  UserMapper userMapper;

    public CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setUserDTO(userMapper.toDTO(entity.getUser()));
        dto.setCategoryParent(entity.getCategoryParent());
        dto.setName(entity.getName());
        dto.setColor(entity.getColor());
        dto.setIcon(entity.getIcon());

        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setIcon(dto.getIcon());
        entity.setUser(userMapper.toEntity(dto.getUserDTO()));
        entity.setCategoryParent(dto.getCategoryParent());

        return entity;
    }
}
