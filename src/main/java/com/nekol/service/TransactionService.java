package com.nekol.service;

import com.nekol.payload.request.TransactionRequest;
import com.nekol.payload.response.MessageResponse;

public interface TransactionService {

    MessageResponse create(TransactionRequest request);

    MessageResponse retrieveOutcome();

    MessageResponse addTransaction(TransactionRequest request);
    MessageResponse detail();

    void delete(Long id);

    MessageResponse updateTransaction(Long id, TransactionRequest request);
}
