package com.nekol.domain.dto;

import com.nekol.payload.request.CreateCategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String icon;
    private String color;
    private UserDTO userDTO;

    public CategoryDTO(CreateCategoryRequest request) {
        name = request.getName();
        icon = request.getIcon();
        color = request.getColor();

    }
}
