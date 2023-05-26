package com.nekol.service.mapper;

import com.nekol.domain.dto.TransactionDTO;
import com.nekol.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionDTO dto) {

        Transaction entity = new Transaction();
        entity.setCode(dto.getCode());
        entity.setNote(dto.getNote());
        entity.setPrice(dto.getPrice());
        entity.setCategory(dto.getCategory());
        entity.setWallet(dto.getWallet());

        return entity;
    }

    public TransactionDTO toDTO(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setWallet(entity.getWallet());
        dto.setCode(entity.getCode());
        dto.setNote(entity.getNote());
        dto.setPrice(entity.getPrice());

        return dto;
    }


}
