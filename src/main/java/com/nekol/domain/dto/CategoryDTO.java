package com.nekol.domain.dto;

import com.nekol.domain.entity.Category;
import com.nekol.payload.request.CategoryRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String icon;
    private String color;
    private UserDTO userDTO;
    private Category categoryParent;

    public CategoryDTO(CategoryRequest request) {
        name = request.getName();
        icon = request.getIcon();
        color = request.getColor();
    }

    public CategoryDTO(Long id, String name, String icon, String color) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
    }


}
