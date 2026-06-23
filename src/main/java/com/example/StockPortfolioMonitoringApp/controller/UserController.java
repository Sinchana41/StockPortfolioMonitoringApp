package com.example.StockPortfolioMonitoringApp.controller;

import com.example.StockPortfolioMonitoringApp.dto.LoginRequest;
import com.example.StockPortfolioMonitoringApp.dto.LoginResponse;
import com.example.StockPortfolioMonitoringApp.dto.RegisterRequest;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.service.UserService;
import com.example.StockPortfolioMonitoringApp.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
public class UserController {

    // @Autowired
    private UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {

        String s = userService.register(request);

        ApiResponse<String> response = new ApiResponse(
                true,
                "Registration success",
                s
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        LoginResponse response = userService.login(request);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User>  users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }
}
