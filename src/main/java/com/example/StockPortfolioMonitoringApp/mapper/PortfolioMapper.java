package com.example.StockPortfolioMonitoringApp.mapper;

import com.example.StockPortfolioMonitoringApp.dto.PortfolioRequest;
import com.example.StockPortfolioMonitoringApp.dto.PortfolioResponse;
import com.example.StockPortfolioMonitoringApp.entity.Portfolio;

/**
 * PortfolioMapper converts between entity and DTO.
 * This keeps controller and service code clean.
 */
public class PortfolioMapper {

    public static Portfolio toEntity(PortfolioRequest request, Long userId){
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(userId);
        portfolio.setName(request.getName());
        portfolio.setBaseCurrency(request.getBaseCurrency());
        portfolio.setDescription(request.getDescription());
        return portfolio;
    }

    public static PortfolioResponse toResponse(Portfolio portfolio) {
     return new PortfolioResponse(
            portfolio.getId(),
            portfolio.getUserId(),
            portfolio.getName(),
            portfolio.getBaseCurrency(),
            portfolio.getDescription()
         );
     }

     public static void updateEntity(Portfolio portfolio, PortfolioRequest request){
        portfolio.setName(request.getName());
        portfolio.setBaseCurrency(request.getBaseCurrency());
        portfolio.setDescription(request.getDescription());
    }
}
