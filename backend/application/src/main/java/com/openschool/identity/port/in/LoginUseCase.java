package com.openschool.identity.port.in;

public interface LoginUseCase {
    String login(String username, String rawPassword);
}
