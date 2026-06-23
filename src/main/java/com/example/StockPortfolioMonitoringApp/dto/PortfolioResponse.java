package com.example.StockPortfolioMonitoringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {

    private Long id;
    private Long userId;
    private String name;
    private String baseCurrency;
    private String description;
}
