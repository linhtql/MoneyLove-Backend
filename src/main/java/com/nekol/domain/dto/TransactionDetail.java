package com.nekol.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionDetail {
    private List<TransactionDTO> transactionDTOS;
    private double total;
}
