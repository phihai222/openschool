package com.openschool.infrastructure.adapter.in.rest.systemsetup;

import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.SystemSetupStatusResponse;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.mapper.SystemSetupDtoMapper;
import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/system-setup")
@AllArgsConstructor
public class SystemSetupController {
    private final GetSystemSetupStatusUseCase getSystemSetupStatusUseCase;

    @GetMapping
    public ResponseEntity<SystemSetupStatusResponse> getSystemSetupStatus() {
        var res = getSystemSetupStatusUseCase.getSystemSetupStatus();
        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }
}
