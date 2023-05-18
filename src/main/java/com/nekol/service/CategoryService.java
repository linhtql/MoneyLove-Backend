package com.nekol.service;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.dto.StatisticDTO;
import com.nekol.payload.request.CategoryRequest;
import com.nekol.payload.response.MessageResponse;

public interface CategoryService {

    MessageResponse create(CategoryRequest request);
    void delete(Long id);
    MessageResponse getByOutCome();
    MessageResponse getByInCome();
    MessageResponse update(Long id, CategoryRequest request);
    MessageResponse getUpdate(String type);

    StatisticDTO statistic(String type);
}
