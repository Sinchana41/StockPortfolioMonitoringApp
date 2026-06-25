package com.example.StockPortfolioMonitoringApp.service.impl;

import com.example.StockPortfolioMonitoringApp.dto.*;
import com.example.StockPortfolioMonitoringApp.entity.PasswordResetToken;
import com.example.StockPortfolioMonitoringApp.entity.Role;
import com.example.StockPortfolioMonitoringApp.repository.PasswordResetTokenRepository;
import com.example.StockPortfolioMonitoringApp.security.JwtUtil;
import com.example.StockPortfolioMonitoringApp.entity.User;
import com.example.StockPortfolioMonitoringApp.exception.InvalidCredentialsException;
import com.example.StockPortfolioMonitoringApp.exception.UserAlreadyExistsException;
import com.example.StockPortfolioMonitoringApp.repository.UserRepository;
import com.example.StockPortfolioMonitoringApp.service.EmailService;
import com.example.StockPortfolioMonitoringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private EmailService emailService;

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

    @Override
    public String forgetPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user == null)
            throw new InvalidCredentialsException("Email not registered");

        String token = UUID.randomUUID().toString(); //Universally Unique Identifier

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setEmail(user.getEmail());
        passwordResetToken.setExpiryTime(LocalDateTime.now().plusMinutes(15));
        passwordResetToken.setUsed(false);
        passwordResetTokenRepository.save(passwordResetToken);

        String resetLink = "http://localhost:8080/app/user/reset-password?token="+token;

        emailService.sendEmail(
                user.getEmail(),
                "Password Reset",
                "click link below:" + resetLink
                );
        return "Password reset mail sent";
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {

        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(request.getToken());
        if(resetToken == null)
            throw new InvalidCredentialsException("Invalid token");

        if(resetToken.getUsed())
            throw  new InvalidCredentialsException("token is already used");

        if(resetToken.getExpiryTime().isBefore(LocalDateTime.now()))
            throw new InvalidCredentialsException("time is expired");

        User user = userRepository.findByEmail(resetToken.getEmail());

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));

        resetToken.setUsed(true);
        userRepository.save(user);
        passwordResetTokenRepository.save(resetToken);
        return "password updated successfully";
    }
}
