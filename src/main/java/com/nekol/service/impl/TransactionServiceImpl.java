package com.nekol.service.impl;

import com.nekol.domain.dto.TransactionDTO;
import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.Wallet;
import com.nekol.payload.request.TransactionRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.CategoryRepository;
import com.nekol.repository.TransactionRepository;
import com.nekol.repository.WalletRepository;
import com.nekol.service.TransactionService;
import com.nekol.service.mapper.TransactionMapper;
import com.nekol.service.mapper.UserMapper;
import com.nekol.service.mapper.WalletMapper;
import com.nekol.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletMapper walletMapper;



    @Override
    public MessageResponse create(TransactionRequest request) {
//        MessageResponse response =
        return null;
    }

    @Override
    public MessageResponse retrieveOutcome() {
        return null;
    }

    @Override
    public MessageResponse addTransaction(TransactionRequest request) {
        MessageResponse response = new MessageResponse("Add Transaction successfully!");
        try {

            TransactionDTO dto = new TransactionDTO(request);
            dto.setCode(StringUtil.generateCode());
            Wallet wallet = walletRepository.findById(request.getWalletId()).orElse(null);
            WalletDTO walletDTO = walletMapper.toDTO(wallet);
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            dto.setCategory(category);
            dto.setWallet(wallet);


            // check xem category la them hay chi
            List<Long> idsChi = categoryRepository.getCategoryByType("Chi");

            if (idsChi.contains(dto.getCategory().getId())) {
                wallet.setAmount(walletDTO.getAmount() - dto.getPrice());
            } else {
                wallet.setAmount(walletDTO.getAmount() + dto.getPrice());
            }


            // cap nhat lai vy
            walletRepository.save(wallet);
            transactionRepository.save(transactionMapper.toEntity(dto));
//            response.setData(dto);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public MessageResponse detail() {
        MessageResponse response = new MessageResponse();
        try {


        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
