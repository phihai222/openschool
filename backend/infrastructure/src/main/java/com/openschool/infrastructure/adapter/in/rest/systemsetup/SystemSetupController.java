package com.openschool.infrastructure.adapter.in.rest.systemsetup;

import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.CreateAdminRequest;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.SystemSetupStatusResponse;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.mapper.SystemSetupDtoMapper;
import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import com.openschool.systemsetup.port.in.SetupAdminUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/system-setup")
public class SystemSetupController {
    private final GetSystemSetupStatusUseCase getSystemSetupStatusUseCase;
    private final SetupAdminUseCase setupAdminUseCase;

    public SystemSetupController(
            @Qualifier("getSystemSetupStatusUseCase") GetSystemSetupStatusUseCase getSystemSetupStatusUseCase,
            @Qualifier("setupAdminUseCase") SetupAdminUseCase setupAdminUseCase) {
        this.getSystemSetupStatusUseCase = getSystemSetupStatusUseCase;
        this.setupAdminUseCase = setupAdminUseCase;
    }

    @GetMapping
    public ResponseEntity<SystemSetupStatusResponse> getSystemSetupStatus() {
        var res = getSystemSetupStatusUseCase.getSystemSetupStatus();
        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }

    @PostMapping("/init-root-user")
    public ResponseEntity<SystemSetupStatusResponse> initRootUser(@RequestBody CreateAdminRequest request) {
        var res = setupAdminUseCase.createAdminUser(
                SystemSetupDtoMapper.toCommand(request)
        );

        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }
}
