package com.loanmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Public status endpoint so visiting the backend root URL
 * shows a friendly status page instead of "Access Denied".
 */
@RestController
public class StatusController {

    private final Instant startTime = Instant.now();

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("service", "WealthFlow Loan Management API");
        info.put("status", "✅ Running");
        info.put("version", "1.0.0");
        info.put("uptime", getUptime());
        info.put("timestamp", Instant.now().toString());
        info.put("docs", "/swagger-ui.html");
        info.put("health", "/actuator/health");
        return ResponseEntity.ok(info);
    }

    private String getUptime() {
        long seconds = Instant.now().getEpochSecond() - startTime.getEpochSecond();
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        if (days > 0) return days + "d " + hours + "h " + minutes + "m " + secs + "s";
        if (hours > 0) return hours + "h " + minutes + "m " + secs + "s";
        if (minutes > 0) return minutes + "m " + secs + "s";
        return secs + "s";
    }
}
