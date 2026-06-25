package com.example.StockPortfolioMonitoringApp.controller;

import com.example.StockPortfolioMonitoringApp.dto.*;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.service.UserService;
import com.example.StockPortfolioMonitoringApp.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
public class UserController {


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

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse<String>> forgetPassword(@RequestBody ForgotPasswordRequest request){

        String s = userService.forgetPassword(request);

        ApiResponse<String> response = new ApiResponse<>(
                true,
                "Email sent",
                  s
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request){

        String s = userService.resetPassword(request);

        return new ResponseEntity<>(new ApiResponse<>(true,"password changed",s),HttpStatus.OK);

    }
}
