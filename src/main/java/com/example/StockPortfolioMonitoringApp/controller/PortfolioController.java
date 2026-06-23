package com.example.StockPortfolioMonitoringApp.controller;


import com.example.StockPortfolioMonitoringApp.dto.PortfolioRequest;
import com.example.StockPortfolioMonitoringApp.dto.PortfolioResponse;
import com.example.StockPortfolioMonitoringApp.service.PortfolioService;
import com.example.StockPortfolioMonitoringApp.utility.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/user/portfolio")
//@PreAuthorize()
public class PortfolioController {

    private PortfolioService portfolioService;
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PortfolioResponse>> createPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest){


        PortfolioResponse response = portfolioService.createPortfolio(portfolioRequest);
        ApiResponse<PortfolioResponse> apiResponse = new ApiResponse<>(
                true,
                "Created",
                 response
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<PortfolioResponse>>> getAll(){
        List<PortfolioResponse>  list = portfolioService.getAll();
        ApiResponse<List<PortfolioResponse>> response = new ApiResponse<>(
                true,
                "Fetched all Portfolio",
                 list
        );
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<PortfolioResponse>> getById(@PathVariable Long id){
        PortfolioResponse response = portfolioService.getById(id);

        ApiResponse<PortfolioResponse> apiResponse = new ApiResponse<>(
                true,
                "Fetched the portfolio",
                response
        );

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
     @PutMapping("/updateById/{id}")
     public ResponseEntity<ApiResponse<PortfolioResponse>>  updateById(@PathVariable Long id,@RequestBody PortfolioRequest request){

        PortfolioResponse response = portfolioService.updateById(id,request);

         ApiResponse<PortfolioResponse> apiResponse = new ApiResponse<>(
                 true,
                 "updated the portfolio",
                 response
         );

         return new ResponseEntity<>(apiResponse,HttpStatus.OK);

     }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PathVariable Long id){

        boolean result = portfolioService.deleteById(id);

        ApiResponse<Boolean> apiResponse = new ApiResponse<>(
                true,
                "portfolio deleted",
                result
        );

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
