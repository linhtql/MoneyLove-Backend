package com.nekol.payload.request;

import lombok.Data;

@Data
public class WalletRequest {

    private String name;
    private String icon;
    private Double amount;
    private String color;
}
