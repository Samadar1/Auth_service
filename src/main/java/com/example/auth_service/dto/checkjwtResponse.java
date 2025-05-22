package com.example.auth_service.dto;

public class checkjwtResponse {
    private String username;

    public checkjwtResponse(String username) {
        this.username = username;
    }

    // Геттеры
    public String getUsername() {
        return username;
    }
}
