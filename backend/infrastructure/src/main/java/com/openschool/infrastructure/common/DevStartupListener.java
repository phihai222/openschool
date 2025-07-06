package com.openschool.infrastructure.common;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DevStartupListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String port = event.getApplicationContext().getEnvironment().getProperty("local.server.port", "8080");
        String contextPath = event.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path", "");
        log.info("🚀 OpenSchool Dev Server started: Swagger UI → http://localhost:" + port + contextPath + "/swagger-ui/index.html");
    }
}