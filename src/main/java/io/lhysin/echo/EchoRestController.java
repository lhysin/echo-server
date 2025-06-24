package io.lhysin.echo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoRestController {

    @Value("${spring.application.name}")
    private String  appName;

    @GetMapping("/app-name")
    public String appName() {
        return appName;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @RequestMapping("/**")
    public String fallback(HttpServletRequest request) {
        return "Echo: " + request.getRequestURI();
    }
}