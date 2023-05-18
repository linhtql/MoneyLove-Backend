package com.nekol.service.impl;

import com.nekol.domain.dto.*;
import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.Wallet;
import com.nekol.payload.request.CategoryRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.CategoryRepository;
import com.nekol.repository.UserRepository;
import com.nekol.repository.WalletRepository;
import com.nekol.service.CategoryService;
import com.nekol.service.mapper.CategoryMapper;
import com.nekol.service.mapper.UserMapper;
import com.nekol.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public MessageResponse create(CategoryRequest request) {

        MessageResponse response = new MessageResponse();

        try {
            CategoryDTO categoryDTO = new CategoryDTO(request);

            categoryDTO.setUserDTO(UserUtil.getUserCurrenUtil());
            if (request.getParent_id() != null ) {
                Category categoryParent = categoryRepository.findById(request.getParent_id()).orElse(null);
                categoryDTO.setCategoryParent(categoryParent);
            } else {
                categoryDTO.setCategoryParent(null);
            }

//            if(categoryRepository.existsByName(categoryDTO.getName())) {
//                response = new MessageResponse("Category is exists!");
//                return response;
//            }
            Category category = categoryMapper.toEntity(categoryDTO);
            categoryRepository.save(category);
            response = new MessageResponse("Create category successfully!");
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
            Long userIdAdmin = userRepository.findByUsername("admin").orElse(null).getId();
            Long userIdUser= userDTO.getId();
            Long categoryId = categoryParent.getId();
            System.out.println(userIdAdmin);
            System.out.println(userIdUser);
            List<CategoryDTO> results = categoryRepository.getCategory(categoryId, userIdAdmin, userIdUser);
            response.setData(results);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public MessageResponse getByInCome() {
        MessageResponse response = new MessageResponse();
        try {
            UserDTO userDTO = UserUtil.getUserCurrenUtil();
            Category categoryParent = categoryRepository.findByName("Thu");
            Long userIdAdmin = userRepository.findByUsername("admin").orElse(null).getId();
            Long userIdUser= userDTO.getId();
            Long categoryId = categoryParent.getId();
            System.out.println(userIdAdmin);
            System.out.println(userIdUser);
            List<CategoryDTO> results = categoryRepository.getCategory(categoryId, userIdAdmin, userIdUser);
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

    @Override
    public MessageResponse getUpdate(String type) {
        Long userId = UserUtil.getUserCurrenUtil().getId();
        MessageResponse messageResponse = new MessageResponse();
        if (type.equals("out-come")) {
            Category categoryParent = categoryRepository.findByName("Chi");
            messageResponse.setData(categoryRepository.getCategoryByUserAndType(categoryParent.getId(),userId));
        } else {
            Category categoryParent = categoryRepository.findByName("Thu");
            messageResponse.setData(categoryRepository.getCategoryByUserAndType(categoryParent.getId(),userId));
        }
        return messageResponse;
    }

    @Override
    public StatisticDTO statistic(String type) {
        UserDTO userDTO = UserUtil.getUserCurrenUtil();
        Category categoryParentIncome = categoryRepository.findByName("Thu");
        CategoryCustomDTO incomeDTO = null;
        double total = 0;
        for(WalletDTO temp :  walletRepository.retrieveAllByUser(userDTO.getId())) {
            total += temp.getAmount();
        }

        List<CategoryCustomDTO> income = new ArrayList<>();
        double totalInCome = 0;

        for(Object[] temp : categoryRepository.statistic(userDTO.getId(), categoryParentIncome.getId())) {

            incomeDTO = new CategoryCustomDTO();
            incomeDTO.setCategory(temp[0].toString());
            incomeDTO.setPrice(Double.parseDouble(temp[1].toString()));
            totalInCome += Double.parseDouble(temp[1].toString());

            income.add(incomeDTO);
        }

        Category categoryParentOutCome = categoryRepository.findByName("Chi");
        List<CategoryCustomDTO> outcome = new ArrayList<>();
        double totalOutCome = 0;

        for(Object[] temp : categoryRepository.statistic(userDTO.getId(), categoryParentOutCome.getId())) {

            incomeDTO = new CategoryCustomDTO();
            incomeDTO.setCategory(temp[0].toString());
            incomeDTO.setPrice(Double.parseDouble(temp[1].toString()));
            totalOutCome += Double.parseDouble(temp[1].toString());

            outcome.add(incomeDTO);
        }

        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setTotalInCome(totalInCome);
        statisticDTO.setTotalOutCome(totalOutCome);
        statisticDTO.setTotal(total);

        if(type.equals("out-come")) {
            statisticDTO.setCategoryCustomDTO(outcome);
        } else {
            statisticDTO.setCategoryCustomDTO(income);
        }

        return statisticDTO;
    }
}
