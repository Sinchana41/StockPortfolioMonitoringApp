package com.example.StockPortfolioMonitoringApp.controller;

import com.example.StockPortfolioMonitoringApp.dto.HoldingRequest;
import com.example.StockPortfolioMonitoringApp.dto.HoldingResponse;
import com.example.StockPortfolioMonitoringApp.service.HoldingService;
import com.example.StockPortfolioMonitoringApp.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user/portfolio/holding")
public class HoldingController {

    public HoldingController(HoldingService holdingService) {
        this.holdingService = holdingService;
    }

    private HoldingService holdingService;

    @PostMapping("/{portfolioId}/create")
    public ResponseEntity<ApiResponse<HoldingResponse>> create(@PathVariable Long portfolioId, @RequestBody HoldingRequest request){

        HoldingResponse holdingResponse = holdingService.create(request,portfolioId);
        ApiResponse<HoldingResponse> response = new ApiResponse<>(
                true,
                "created successfully",
                holdingResponse
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{portfolioId}/getAllHoldings")
    public ResponseEntity<ApiResponse<List<HoldingResponse>>> getAllHoldings(@PathVariable Long portfolioId){

        List<HoldingResponse> holdingResponses = holdingService.getAllHoldings(portfolioId);

        ApiResponse<List<HoldingResponse>> response  = new ApiResponse<>(
                true,
                "fetched all holdings",
                holdingResponses
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{portfolioId}/getHoldingById/{holdingId}")
    public ResponseEntity<ApiResponse<HoldingResponse>> getHoldingById(@PathVariable Long portfolioId,@PathVariable Long holdingId){

        HoldingResponse holdingResponses = holdingService.getHoldingById(portfolioId,holdingId);

        ApiResponse<HoldingResponse> response  = new ApiResponse<>(
                true,
                "fetched the holding",
                holdingResponses
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/updateHolding/{holdingId}")
    public ResponseEntity<ApiResponse<HoldingResponse>> updateHoldingById(@PathVariable Long holdingId,@RequestBody HoldingRequest request){
        HoldingResponse response = holdingService.updateHoldingById(holdingId,request);
        return new ResponseEntity<>(new ApiResponse<>(true,"holding updated",response),HttpStatus.OK);
    }

    @DeleteMapping("/deleteHolding/{holdingId}")
    public ResponseEntity<ApiResponse<String>> deleteHoldingById(@PathVariable Long holdingId){
        return new ResponseEntity<>(new ApiResponse<>(true,"holding deleted",holdingService.deleteHoldingById(holdingId)),HttpStatus.OK);
    }
}
