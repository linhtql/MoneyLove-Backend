package com.nekol.service.mapper;

import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    @Autowired
    UserMapper userMapper;

    public WalletDTO toDTO (Wallet entity) {

        WalletDTO dto = new WalletDTO();
        dto.setId(entity.getId());
        dto.setColor(entity.getColor());
        dto.setIcon(entity.getIcon());
        dto.setName(entity.getName());
        dto.setAmount(entity.getAmount());

        return dto;
    }

    public Wallet toEntity (WalletDTO dto) {
        Wallet entity = new Wallet();
        entity.setName(dto.getName());
        entity.setIcon(dto.getIcon());
        entity.setColor(dto.getColor());
        entity.setAmount(dto.getAmount());
        entity.setUser(userMapper.toEntity(dto.getUserDTO()));
        return entity;
    }


}
