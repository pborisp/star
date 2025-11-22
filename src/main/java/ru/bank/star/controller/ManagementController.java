package ru.bank.star.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import ru.bank.star.configuration.CachConfig;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/management")
public class ManagementController {
    private final CachConfig cacheConfig;

    @Autowired
    public ManagementController(CachConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

    @PostMapping("/clear-caches")
    public ResponseEntity<?> clearCaches() {
        cacheConfig.clearRecommendationsCache();
        return ResponseEntity.ok().body("Кэш успешно очищен.");
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return new LinkedHashMap<String, Object>() {{
            put("appName", "AppBankStar");
            put("appVersion", "v3.0.0");
        }};
    }
}
