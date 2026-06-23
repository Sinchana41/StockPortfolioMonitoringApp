package com.example.StockPortfolioMonitoringApp.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private  Long id;
     @Column(name = "name",nullable = false)
     private String name;
     @Column(name = "email",nullable = false,unique = true)
     private String email;
     @Column(name = "passwordHash",nullable = false)
     private String passwordHash;
     @Column(name = "role")
     @Enumerated(EnumType.STRING)
     private Role role;
     @Column(name = "status")
     private String status;

}
