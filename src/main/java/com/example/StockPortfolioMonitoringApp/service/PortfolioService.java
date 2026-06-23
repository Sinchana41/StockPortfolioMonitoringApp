package com.example.StockPortfolioMonitoringApp.service;

import com.example.StockPortfolioMonitoringApp.dto.PortfolioRequest;
import com.example.StockPortfolioMonitoringApp.dto.PortfolioResponse;

import java.util.List;

public interface PortfolioService {
    PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest);

    List<PortfolioResponse> getAll();

    PortfolioResponse getById(Long id);

    boolean deleteById(Long id);

    PortfolioResponse updateById(Long id,PortfolioRequest request);
}
