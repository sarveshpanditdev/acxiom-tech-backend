package com.acxiom_tech_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_master")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "skills")
    private String skills;
    
    @Column(name = "hobbies")
    private String hobbies;
    
    @Column(name = "photo_path")
    private String photoPath;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "mobile_no", unique = true, nullable = false)
    private String mobileNo;

}

