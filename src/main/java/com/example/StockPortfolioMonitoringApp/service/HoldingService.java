package com.example.StockPortfolioMonitoringApp.service;

import com.example.StockPortfolioMonitoringApp.dto.HoldingRequest;
import com.example.StockPortfolioMonitoringApp.dto.HoldingResponse;

import java.util.List;

public interface HoldingService {
    HoldingResponse create(HoldingRequest request,Long portfolioId);

    List<HoldingResponse> getAllHoldings(Long portfolioId);

    HoldingResponse getHoldingById(Long portfolioId, Long holdingId);

    HoldingResponse updateHoldingById(Long holdingId, HoldingRequest request);

    String deleteHoldingById(Long holdingId);
}
