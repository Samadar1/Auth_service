package com.example.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;



@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}