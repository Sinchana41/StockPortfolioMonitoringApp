package com.example.StockPortfolioMonitoringApp.exception;

public class PortfolioAlreadyExistsException extends RuntimeException{
    public PortfolioAlreadyExistsException(String message) {
        super(message);
    }
}
