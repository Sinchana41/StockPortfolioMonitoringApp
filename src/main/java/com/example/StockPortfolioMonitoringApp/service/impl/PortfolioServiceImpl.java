package com.example.StockPortfolioMonitoringApp.service.impl;

import com.example.StockPortfolioMonitoringApp.dto.PortfolioRequest;
import com.example.StockPortfolioMonitoringApp.dto.PortfolioResponse;
import com.example.StockPortfolioMonitoringApp.entity.Portfolio;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.exception.InvalidCredentialsException;
import com.example.StockPortfolioMonitoringApp.exception.PortfolioAlreadyExistsException;
import com.example.StockPortfolioMonitoringApp.mapper.PortfolioMapper;
import com.example.StockPortfolioMonitoringApp.repository.PortfolioRepository;
import com.example.StockPortfolioMonitoringApp.repository.UserRepository;
import com.example.StockPortfolioMonitoringApp.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest) {
        String email = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        User user = userRepository.findByEmail(email);

        if(portfolioRepository.existsByUserIdAndName(user.getId(),portfolioRequest.getName())){
            throw new PortfolioAlreadyExistsException("Portfolio already exist by this name");
        }

        Portfolio portfolio = PortfolioMapper.toEntity(portfolioRequest, user.getId());
        Portfolio portfolio1 = portfolioRepository.save(portfolio);
        return PortfolioMapper.toResponse(portfolio1);
    }

    @Override
    public List<PortfolioResponse> getAll() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(email);
        List<PortfolioResponse>  list = new ArrayList<>();

        List<Portfolio> portfolios = portfolioRepository.findAll();

        for (Portfolio portfolio:portfolios){
           if(portfolio.getUserId() == user.getId()){
               list.add(PortfolioMapper.toResponse(portfolio));
           }
        }
        return list;
    }

    @Override
    public PortfolioResponse getById(Long id) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(email);

        Optional<Portfolio> optional =  portfolioRepository.findById(id);
        Portfolio portfolio = optional.get();
        if(user.getId() == portfolio.getUserId()) {
            return PortfolioMapper.toResponse(portfolio);
        }else
        throw new InvalidCredentialsException("user is different");
    }

    @Override
    public boolean deleteById(Long id) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(email);

       Optional<Portfolio> optional =  portfolioRepository.findById(id);
       Portfolio portfolio = optional.get();
       if(user.getId() == portfolio.getUserId()){
           portfolioRepository.deleteById(id);
           return true;
       }else throw  new InvalidCredentialsException("user id different");

    }

    @Override
    public PortfolioResponse updateById(Long id,PortfolioRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(email);

        Optional<Portfolio> optional =  portfolioRepository.findById(id);

        Portfolio portfolio = optional.get();

        if(user.getId() == portfolio.getUserId()){
            PortfolioMapper.updateEntity(portfolio,request);
            Portfolio portfolio1 = portfolioRepository.save(portfolio);
            return PortfolioMapper.toResponse(portfolio1);
        }else
        throw  new InvalidCredentialsException("user is different");
    }
}
