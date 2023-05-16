package com.nekol.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private String note;
    private Double price;
    private Long categoryId;
    private Long walletId;

}
