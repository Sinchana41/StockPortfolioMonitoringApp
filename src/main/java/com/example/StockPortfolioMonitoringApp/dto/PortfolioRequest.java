package com.example.StockPortfolioMonitoringApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PortfolioRequest {

    @NotBlank
    private String name;
    @NotBlank
//    @Positive
    private String baseCurrency;
    private String description;
}
