package com.example.StockPortfolioMonitoringApp.service;

import com.example.StockPortfolioMonitoringApp.dto.*;
import com.example.StockPortfolioMonitoringApp.entity.User;

import java.util.List;

public interface UserService {
    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    List<User> getAllUsers();

    String forgetPassword(ForgotPasswordRequest request);

    String resetPassword(ResetPasswordRequest request);
}
