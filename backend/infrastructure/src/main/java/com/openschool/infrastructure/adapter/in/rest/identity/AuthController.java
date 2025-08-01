package com.openschool.infrastructure.adapter.in.rest.identity;

import com.openschool.identity.port.in.LoginUseCase;
import com.openschool.infrastructure.adapter.in.rest.identity.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}

