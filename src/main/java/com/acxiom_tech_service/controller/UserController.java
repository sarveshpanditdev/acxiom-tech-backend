package com.acxiom_tech_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acxiom_tech_service.dto.ApiResponseDto;
import com.acxiom_tech_service.dto.UserDetailsDto;
import com.acxiom_tech_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<?>> login(@RequestBody UserDetailsDto userDetailsDto) {

        if (userDetailsDto.getUserName() == null || userDetailsDto.getPassword() == null) {
            return ResponseEntity.badRequest().body(
                ApiResponseDto.error("Login failed", "Username or password missing", 400)
            );
        }

        boolean loggedIn = userService.login(userDetailsDto.getUserName(), userDetailsDto.getPassword());

        if (loggedIn) {
            return ResponseEntity.ok(
                ApiResponseDto.success("Login successful", null, 200)
            );
        } else {
            return ResponseEntity.status(401).body(
                ApiResponseDto.error("Login failed", "Invalid username or password", 401)
            );
        }
    }
    
    
    @PostMapping("/submitUserDetails")
    public ResponseEntity<ApiResponseDto<?>> submitUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        try {
            boolean isUserDetailSaved = userService.submitUserDetails(userDetailsDto);
            return ResponseEntity.ok(
                ApiResponseDto.success("User details submitted successfully", isUserDetailSaved, 200)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                ApiResponseDto.error("Failed to submit user details", e.getMessage(), 500)
            );
        }
    }
    
}
