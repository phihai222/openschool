package com.openschool.infrastructure.rest;

import com.openschool.identity.InitRootUserUseCase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final InitRootUserUseCase initRootUserUseCase;

    public AuthController(InitRootUserUseCase initRootUserUseCase) {
        this.initRootUserUseCase = initRootUserUseCase;
    }

    @PostMapping("/init-root")
    public ResponseEntity<?> initRoot(@RequestBody InitRootRequest request) {
        initRootUserUseCase.initRoot(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}

@Getter
@Setter
class InitRootRequest {
    private String username;
    private String password;
}
