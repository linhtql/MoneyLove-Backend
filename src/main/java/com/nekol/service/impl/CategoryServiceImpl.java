package com.nekol.service.impl;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.entity.Category;
import com.nekol.domain.enumeration.AlertMessage;
import com.nekol.payload.request.CategoryRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.CategoryRepository;
import com.nekol.service.CategoryService;
import com.nekol.service.mapper.CategoryMapper;
import com.nekol.service.mapper.UserMapper;
import com.nekol.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public MessageResponse create(CategoryRequest request) {

        MessageResponse response = new MessageResponse();

        try {
            CategoryDTO categoryDTO = new CategoryDTO(request);

            categoryDTO.setUserDTO(UserUtil.getUserCurrenUtil());
            Category categoryParent = categoryRepository.findById(request.getParent_id()).orElse(null);
            categoryDTO.setCategoryParent(categoryParent);
//            if(categoryRepository.existsByName(categoryDTO.getName())) {
//                response = new MessageResponse("Category is exists!");
//                return response;
//            }
            Category category = categoryMapper.toEntity(categoryDTO);
            categoryRepository.save(category);
            response = new MessageResponse("Create category successfully!");
            response.setData(categoryDTO);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public MessageResponse getByOutCome() {
        MessageResponse response = new MessageResponse();
        try {
            UserDTO userDTO = UserUtil.getUserCurrenUtil();
            Category categoryParent = categoryRepository.findByName("Chi");
            Long userId= userDTO.getId();
            Long categoryId = categoryParent.getId();
            List<CategoryDTO> results = categoryRepository.getCategory(categoryId, userId);
            response.setData(results);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public MessageResponse update(Long id, CategoryRequest request) {
        MessageResponse response = new MessageResponse();
        try {
            Category oldCategory = categoryRepository.findById(id).orElse(null);
            if (oldCategory == null) {
                response.setMessage("Can't find category: " + id);
            } else {
                Category categoryParent = categoryRepository.findById(request.getParent_id()).orElse(null);
                oldCategory.setCategoryParent(categoryParent);
                oldCategory.setName(request.getName());
                oldCategory.setIcon(request.getIcon());
                oldCategory.setColor(request.getColor());

                categoryRepository.save(oldCategory);

//                response.setData(categoryMapper.toDTO(oldCategory));
                response.setMessage("Update category successfully!");
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
