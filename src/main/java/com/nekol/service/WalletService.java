package com.nekol.service;

import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Wallet;
import com.nekol.payload.request.WalletRequest;
import com.nekol.payload.response.MessageResponse;

public interface WalletService {

    MessageResponse createWallet(WalletRequest request);
    MessageResponse retrieveAllByUserId();
    WalletDTO updateWallet(WalletRequest request, Long id);
    MessageResponse retrieveById(Long id);

    void deleteWallet(Long id);
}
