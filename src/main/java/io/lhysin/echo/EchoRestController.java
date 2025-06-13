package io.lhysin.echo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class EchoRestController {

    @Value("${spring.application.name}")
    private String  appName;

    @GetMapping("/app-name")
    public String appName() {
        return appName;
    }//asdasdasdasdadaaaaa

    @GetMapping("/high-calculation")
    public String highCalculation() {
        Random random = new Random();
        int sleepMillis = 100 + random.nextInt(901); // 100 ~ 1000ms (100 + 0~900)

        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "High calculation took " + sleepMillis + "ms";
    }

    @RequestMapping("/**")
    public String fallback(HttpServletRequest request) {
        return "Echo: " + request.getRequestURI();
    }
}