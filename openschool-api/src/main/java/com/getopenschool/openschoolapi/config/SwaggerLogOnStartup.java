package com.getopenschool.openschoolapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class SwaggerLogOnStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String baseUrl = "http://localhost:" + serverPort + contextPath;
        System.out.println("\n🚀 Swagger UI: " + baseUrl + "/swagger-ui/index.html\n");
    }
}
