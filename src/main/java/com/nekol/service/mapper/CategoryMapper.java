package com.nekol.service.mapper;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();

        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        Category entity = new Category();

        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setIcon(dto.getIcon());
        return entity;
    }
}
