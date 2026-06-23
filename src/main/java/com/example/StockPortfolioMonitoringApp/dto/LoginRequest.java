package com.example.StockPortfolioMonitoringApp.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String passwordHash;
}
