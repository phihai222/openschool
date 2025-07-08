package com.openschool.infrastructure.adapter.in.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class DebugController {
    @GetMapping("/api/debug-token")
    public String debugToken(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();

        return "Hello, your ID is: " + userId;
    }
}