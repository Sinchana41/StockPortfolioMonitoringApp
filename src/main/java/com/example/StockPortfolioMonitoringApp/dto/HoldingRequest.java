package com.example.StockPortfolioMonitoringApp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HoldingRequest {
    @NotBlank(message = "Stock symbol is required")
    private String symbol;
    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.0001", message = "Quantity must be greater than 0")
    private BigDecimal quantity;
    @NotNull(message = "Buy price is required")
    @DecimalMin(value = "0.0001", message = "Buy price must be greater than 0")
    private BigDecimal buyPrice;
}