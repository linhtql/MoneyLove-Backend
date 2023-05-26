package com.nekol.service.impl;

import com.nekol.domain.dto.TransactionDTO;
import com.nekol.domain.dto.TransactionDetail;
import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.Transaction;
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
import com.nekol.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Map<LocalDate, List<TransactionDTO>> results = new HashMap<>();
            List<TransactionDTO> transactionDTOS = null;

            List<Long> categoriesOutCome = categoryRepository.getCategoryByType("Chi");
                    UserDTO userDTO = UserUtil.getUserCurrenUtil();
            for(TransactionDTO temp : transactionRepository.getTransactionByMonth(userDTO.getId())) {

                if (!results.containsKey(temp.getModifiedDate())) {
                   transactionDTOS = new ArrayList<>();
                    transactionDTOS.add(temp);

                   results.put(temp.getModifiedDate(), transactionDTOS);
                } else {
                    results.get(temp.getModifiedDate()).add(temp);
                }
            }


            Map<LocalDate, TransactionDetail> test = new HashMap<>();
            TransactionDetail transactionDetail = null;

            for (Map.Entry<LocalDate, List<TransactionDTO>> temp : results.entrySet()) {
                double total = 0;
                for(TransactionDTO t : temp.getValue()) {
                    if (categoriesOutCome.contains(t.getId())) {
                        t.setPrefix("-");
                        t.setColor("COLORS.red");
                        total -= t.getPrice();
                    } else {
                        t.setPrefix("+");
                        t.setColor("COLORS.green");
                        total += t.getPrice();
                    }

                }
                transactionDetail = new TransactionDetail();
                transactionDetail.setTransactionDTOS(temp.getValue());
                transactionDetail.setTotal(total);

                test.put(temp.getKey(), transactionDetail);
            }

            response.setData(test);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public MessageResponse updateTransaction(Long id, TransactionRequest request) {
        MessageResponse response = new MessageResponse();
        try {
            TransactionDTO dto = new TransactionDTO(request);
            Transaction oldTransaction = transactionRepository.findById(id).orElse(null);
            if (oldTransaction == null) {
                response.setMessage("Can't find transaction id");
            } else {
                Wallet wallet = walletRepository.findById(request.getWalletId()).orElse(null);
                WalletDTO walletDTO = walletMapper.toDTO(wallet);
                Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
                dto.setCategory(category);
                dto.setWallet(wallet);


                // check xem category la them hay chi
                List<Long> idsChi = categoryRepository.getCategoryByType("Chi");

                if (idsChi.contains(dto.getCategory().getId())) {
                    wallet.setAmount(walletDTO.getAmount() + oldTransaction.getPrice() - dto.getPrice());
                } else {
                    wallet.setAmount(walletDTO.getAmount() - oldTransaction.getPrice() + dto.getPrice());
                }


                // cap nhat lai vy
                walletRepository.save(wallet);
                oldTransaction.setCategory(dto.getCategory());
                oldTransaction.setWallet(dto.getWallet());
                oldTransaction.setNote(dto.getNote());
                oldTransaction.setPrice(dto.getPrice());
                transactionRepository.save(oldTransaction);
            }

        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
