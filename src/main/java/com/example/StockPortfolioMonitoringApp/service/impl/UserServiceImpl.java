package com.example.StockPortfolioMonitoringApp.service.impl;

import com.example.StockPortfolioMonitoringApp.entity.Role;
import com.example.StockPortfolioMonitoringApp.security.JwtUtil;
import com.example.StockPortfolioMonitoringApp.dto.LoginRequest;
import com.example.StockPortfolioMonitoringApp.dto.LoginResponse;
import com.example.StockPortfolioMonitoringApp.dto.RegisterRequest;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.exception.InvalidCredentialsException;
import com.example.StockPortfolioMonitoringApp.exception.UserAlreadyExistsException;
import com.example.StockPortfolioMonitoringApp.repository.UserRepository;
import com.example.StockPortfolioMonitoringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("user already exist by this email");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));

        user.setRole(Role.USER);
        user.setStatus("Active");

        userRepository.save(user);

        return "user registered successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user == null)
            throw new InvalidCredentialsException("user not found");

        if(!passwordEncoder.matches(request.getPasswordHash(), user.getPasswordHash()))
                throw new InvalidCredentialsException("invalid password");

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

    @Override
    public List<User> getAllUsers() {

        return  userRepository.findAll();
    }
}
