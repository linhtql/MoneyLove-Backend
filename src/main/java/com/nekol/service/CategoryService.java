package com.nekol.service;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.payload.request.CategoryRequest;
import com.nekol.payload.response.MessageResponse;

public interface CategoryService {

    MessageResponse create(CategoryRequest request);
    void delete(Long id);
    MessageResponse getByOutCome();
    MessageResponse update(Long id, CategoryRequest request);
}
