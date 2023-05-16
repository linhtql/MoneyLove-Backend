package com.nekol.payload.request;

import com.nekol.domain.entity.Transaction;
import com.nekol.domain.entity.User;
import lombok.Data;

@Data
public class CategoryRequest {

    private String name;
    private String icon;
    private String color;
}
