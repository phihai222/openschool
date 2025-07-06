package com.openschool.identity.port.in;

public interface InitRootUserUseCase {
    void initRoot(String username, String rawPassword);
}