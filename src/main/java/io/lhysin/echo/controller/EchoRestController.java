package io.lhysin.echo.controller;

import io.lhysin.echo.component.ApiCallCounter;
import io.lhysin.echo.model.FallbackResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoRestController {

    private final ApiCallCounter apiCallCounter;

    public EchoRestController(ApiCallCounter apiCallCounter) {
        this.apiCallCounter = apiCallCounter;
    }

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/app-name")
    public String appName() {
        return appName;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @RequestMapping("/**")
    public FallbackResponse fallback(HttpServletRequest request) {
        return FallbackResponse.builder()
            .count(apiCallCounter.incrementAndGet())
            .fromHttpRequest(request)
            .build();
    }
}