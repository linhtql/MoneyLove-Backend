package com.nekol.domain.dto;

import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.Wallet;
import com.nekol.payload.request.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String code;
    private String note;
    private Double price;
    private Category category;
    private Wallet wallet;
    private Date createDate;
    private String username;

    public TransactionDTO(TransactionRequest request) {
        this.note = request.getNote();
        this.price = request.getPrice();
    }

    public TransactionDTO(Category category, String note, String username, Double price) {
        this.category = category;
        this.note = note;
        this.username = username;
        this.price = price;
    }
}
