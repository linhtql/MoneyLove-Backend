package com.nekol.service;

import com.nekol.payload.request.CreateCategoryRequest;
import com.nekol.payload.response.MessageResponse;

public interface CategoryService {

    MessageResponse create(CreateCategoryRequest request);
}
