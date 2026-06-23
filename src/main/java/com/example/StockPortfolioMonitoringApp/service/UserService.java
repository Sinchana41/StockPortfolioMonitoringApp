package com.example.StockPortfolioMonitoringApp.service;

import com.example.StockPortfolioMonitoringApp.dto.LoginRequest;
import com.example.StockPortfolioMonitoringApp.dto.LoginResponse;
import com.example.StockPortfolioMonitoringApp.dto.RegisterRequest;
import com.example.StockPortfolioMonitoringApp.entity.User;

import java.util.List;

public interface UserService {
    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    List<User> getAllUsers();
}
