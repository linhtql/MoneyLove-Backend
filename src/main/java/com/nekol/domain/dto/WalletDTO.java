package com.nekol.domain.dto;

import com.nekol.payload.request.WalletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDTO {

    private Long id;
    private String name;
    private String icon;
    private Double amount;
    private String color;
    private UserDTO userDTO;

    public WalletDTO(WalletRequest request) {
        name = request.getName();
        icon = request.getIcon();
        amount = request.getAmount();
        color = request.getColor();
    }

    public WalletDTO(String name, String icon, Double amount, String color) {
        this.name = name;
        this.icon = icon;
        this.amount = amount;
        this.color = color;
    }


}
