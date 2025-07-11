package com.openschool.infrastructure.adapter.in.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Debug role endpoint
    @GetMapping("/api/debug-role")
    public String debugRole(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities() != null) {
            return "Your roles: " + authentication.getAuthorities().toString();
        } else {
            return "No roles found for the current user.";
        }
    }

    @PreAuthorize("hasRole('ROOT')")
    @GetMapping("/api/debug-test-root-role")
    public String debugTestRootRole(Authentication authentication) {
        return "Hello Root, your ID is: " + authentication.getPrincipal();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/debug-test-admin-role")
    public String debugTestAdminRole(Authentication authentication) {
        return "Hello Admin, your ID is: " + authentication.getPrincipal();
    }
}