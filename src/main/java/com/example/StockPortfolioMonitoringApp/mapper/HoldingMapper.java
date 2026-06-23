package com.example.StockPortfolioMonitoringApp.mapper;

import com.example.StockPortfolioMonitoringApp.dto.HoldingRequest;
import com.example.StockPortfolioMonitoringApp.dto.HoldingResponse;
import com.example.StockPortfolioMonitoringApp.entity.Holding;

public class HoldingMapper {
    public static Holding toEntity(HoldingRequest request, Long portfolioId){
        Holding holding = new Holding();
        holding.setPortfolioId(portfolioId);
        holding.setSymbol(request.getSymbol());
        holding.setQuantity(request.getQuantity());
        holding.setBuyPrice(request.getBuyPrice());
        return holding;
    }

    public static HoldingResponse toResponse(Holding holding) {

    return new HoldingResponse(
            holding.getId(),
            holding.getPortfolioId(),
            holding.getSymbol(),
            holding.getQuantity(),
            holding.getBuyPrice()
    );
    }

    public static void updateEntity(Holding holding, HoldingRequest request){
        holding.setSymbol(request.getSymbol());
        holding.setQuantity(request.getQuantity());
        holding.setBuyPrice(request.getBuyPrice());
        }
   }
