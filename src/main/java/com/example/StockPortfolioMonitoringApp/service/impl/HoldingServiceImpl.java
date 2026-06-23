package com.example.StockPortfolioMonitoringApp.service.impl;

import com.example.StockPortfolioMonitoringApp.dto.HoldingRequest;
import com.example.StockPortfolioMonitoringApp.dto.HoldingResponse;
import com.example.StockPortfolioMonitoringApp.entity.Holding;
import com.example.StockPortfolioMonitoringApp.entity.Portfolio;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.exception.InvalidCredentialsException;
import com.example.StockPortfolioMonitoringApp.mapper.HoldingMapper;
import com.example.StockPortfolioMonitoringApp.repository.HoldingRepository;
import com.example.StockPortfolioMonitoringApp.repository.PortfolioRepository;
import com.example.StockPortfolioMonitoringApp.repository.UserRepository;
import com.example.StockPortfolioMonitoringApp.service.HoldingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoldingServiceImpl implements HoldingService {

    private HoldingRepository holdingRepository;
    private UserRepository userRepository;
    private PortfolioRepository portfolioRepository;

    public HoldingServiceImpl(HoldingRepository holdingRepository, UserRepository userRepository, PortfolioRepository portfolioRepository) {
        this.holdingRepository = holdingRepository;
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public HoldingResponse create(HoldingRequest request,Long portfolioId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email);
        Portfolio portfolio = portfolioRepository.findById(portfolioId).get();

        if(user.getId() == portfolio.getUserId()) {
            Holding holding = holdingRepository.save(HoldingMapper.toEntity(request,portfolioId));
            return HoldingMapper.toResponse(holding);
        }else{
            throw new InvalidCredentialsException("user is different");
        }
    }

    @Override
    public List<HoldingResponse> getAllHoldings(Long portfolioId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email);
        Portfolio portfolio = portfolioRepository.findById(portfolioId).get();

        if(user.getId() == portfolio.getUserId()) {

            List<Holding> list = holdingRepository.findAll();
            List<HoldingResponse> responses =  new ArrayList<>();

            for (Holding holding : list){
                if(holding.getPortfolioId() == portfolioId) {
                    responses.add(HoldingMapper.toResponse(holding));
                }
            }
            return responses;
        }else{
            throw new InvalidCredentialsException("user is different");
        }
    }

    @Override
    public HoldingResponse getHoldingById(Long portfolioId, Long holdingId) {



        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email);
        Portfolio portfolio = portfolioRepository.findById(portfolioId).get();

        if(user.getId() == portfolio.getUserId()) {

            Holding holding = holdingRepository.findById(holdingId).get();
            if(holding.getPortfolioId() == portfolioId) {
               return HoldingMapper.toResponse(holding);
            }else
                throw  new InvalidCredentialsException("portfolio id is different");
        }
        else
            throw new InvalidCredentialsException("user is different");

    }

    @Override
    public HoldingResponse updateHoldingById(Long holdingId, HoldingRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email);

        Holding holding = holdingRepository.findById(holdingId).get();

        Portfolio portfolio = portfolioRepository.findById(holding.getPortfolioId()).get();

        if(user.getId() == portfolio.getUserId() && portfolio.getId() == holding.getPortfolioId()){
            HoldingMapper.updateEntity(holding,request);
            Holding holding1 = holdingRepository.save(holding);
           return HoldingMapper.toResponse(holding1);
        }
        throw new InvalidCredentialsException("user is different");
    }

    @Override
    public String deleteHoldingById(Long holdingId) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email);

        Holding holding = holdingRepository.findById(holdingId).get();

        Portfolio portfolio = portfolioRepository.findById(holding.getPortfolioId()).get();

        if(user.getId() == portfolio.getUserId() && portfolio.getId() == holding.getPortfolioId()){
           holdingRepository.deleteById(holdingId);
           return "holding of id "+holdingId+" is deleted";
        }
        throw new InvalidCredentialsException("user is different");
    }

}
