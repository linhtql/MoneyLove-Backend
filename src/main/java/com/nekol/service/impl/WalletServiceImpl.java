package com.nekol.service.impl;

import com.nekol.domain.dto.UserDTO;
import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Wallet;
import com.nekol.domain.enumeration.AlertMessage;
import com.nekol.payload.request.WalletRequest;
import com.nekol.payload.response.MessageResponse;
import com.nekol.repository.WalletRepository;
import com.nekol.service.WalletService;
import com.nekol.service.mapper.WalletMapper;
import com.nekol.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Override
    public MessageResponse createWallet(WalletRequest walletRequest) {

        MessageResponse response = new MessageResponse(AlertMessage.REGISTER_SUCCESSFUL.getMessage());

        try {
            WalletDTO walletDTO = new WalletDTO(walletRequest);
            walletDTO.setUserDTO(UserUtil.getUserCurrenUtil());
            walletRepository.save(walletMapper.toEntity(walletDTO));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public MessageResponse retrieveAllByUserId() {
        MessageResponse response = new MessageResponse();

        try {
            UserDTO userDTO = UserUtil.getUserCurrenUtil();
            response.setMessage("Query wallet successfully!");
            response.setData(walletRepository.retrieveAllByUser(userDTO.getId()));

        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public WalletDTO updateWallet(WalletRequest request, Long id) {
        WalletDTO dto = null;

        try {
            Wallet wallet = walletRepository.findById(id).orElse(null);
            dto = new WalletDTO(request);
            wallet.setName(dto.getName());
            wallet.setIcon(dto.getIcon());
            wallet.setAmount(dto.getAmount());
            wallet.setColor(dto.getColor());
            walletRepository.save(wallet);


        } catch (Exception e) {
            e.getMessage();
        }
        return dto;
    }

    @Override
    public MessageResponse retrieveById(Long id) {
        MessageResponse response = new MessageResponse();
        try {
            Wallet walletOptional = walletRepository.findById(id).orElse(null);

            if (walletOptional == null) {
                response.setMessage("Can not find wallet!");
            }
            WalletDTO walletDTO = walletMapper.toDTO(walletOptional);
            response.setData(walletDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
