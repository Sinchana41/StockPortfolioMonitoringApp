package com.example.StockPortfolioMonitoringApp.repository;

import com.example.StockPortfolioMonitoringApp.entity.Portfolio;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {


    boolean existsByUserIdAndName(Long id, @NotBlank String name);
}
