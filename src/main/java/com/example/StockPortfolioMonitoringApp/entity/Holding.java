package com.example.StockPortfolioMonitoringApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Holding entity represents one stock position inside a portfolio.
 * Each holding belongs to one portfolio.
 */
@Entity
@Table(name =  "holdings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long portfolioId;

     //Stock symbol like TCS, INFY, HDFCBANK.
    @Column(nullable = false)
    private String symbol;

     //Quantity of shares owned.
     //BigDecimal is used because financial values should not use float/d

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal quantity;

    //Buy price per share
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal buyPrice;

}
