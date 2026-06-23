package com.example.StockPortfolioMonitoringApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Portfolios")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String baseCurrency;
    @Column(length = 1000)
    private String description;
}
