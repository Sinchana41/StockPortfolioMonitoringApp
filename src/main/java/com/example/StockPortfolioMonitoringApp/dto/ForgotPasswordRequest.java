package com.example.StockPortfolioMonitoringApp.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgotPasswordRequest {

    @Email
    private String email;
}
