package com.nekol.domain.dto;

import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.Wallet;
import com.nekol.payload.request.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO{
    private Long id;
    private String code;
    private String note;
    private Double price;
    private Category category;
    private CategoryDTO categoryDTO;
    private Wallet wallet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDate;
    private String username;
    private String color;
    private String prefix;
    public TransactionDTO(TransactionRequest request) {
        this.note = request.getNote();
        this.price = request.getPrice();
    }


    public TransactionDTO(Long tId, Long cId, String cName, String cIcon, String cColor, String tNote, Double tPrice, Date tDate, String username) {
        this.categoryDTO = new CategoryDTO(cId, cName, cIcon, cColor);
        this.id = tId;
        this.note = tNote;
        this.price = tPrice;
        this.modifiedDate = tDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.username = username;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", note='" + note + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", wallet=" + wallet +
                '}';
    }
}
