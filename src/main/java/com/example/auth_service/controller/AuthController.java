package com.example.auth_service.controller;

import com.example.auth_service.dto.*;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthenticationService;
import com.example.auth_service.service.JwtService;
import com.example.auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public AuthController(AuthenticationService authenticationService, JwtService jwtService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "сверка пользователей")
    @PostMapping("/check-jwt")
    public ResponseEntity<checkjwtResponse> extractUsername(@RequestBody checkjwtRequest request) {
        try {
            String username = jwtService.extractUserName(request.getToken());
            return ResponseEntity.ok(new checkjwtResponse(username));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/email")
    public ResponseEntity<String> getEmailByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(user -> new ResponseEntity<>(user.getEmail(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND));
    }

}