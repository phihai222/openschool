package com.openschool.identity;

public interface InitRootUserUseCase {
    void initRoot(String username, String rawPassword);
}