package com.nekol.payload.request;

import com.nekol.domain.entity.User;
import lombok.Data;

@Data
public class CreateCategoryRequest {

    private Long id;
    private String name;
    private String icon;
    private String color;
    private User user;
}
