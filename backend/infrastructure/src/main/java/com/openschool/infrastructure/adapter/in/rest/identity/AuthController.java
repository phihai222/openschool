package com.openschool.infrastructure.adapter.in.rest.identity;

import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.infrastructure.adapter.in.rest.identity.dto.InitRootRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
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

