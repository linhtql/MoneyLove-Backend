package com.nekol.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticDTO {

    private Double total;
    private Double totalOutCome;
    private Double totalInCome;
    private List<CategoryCustomDTO> categoryCustomDTO;

}
