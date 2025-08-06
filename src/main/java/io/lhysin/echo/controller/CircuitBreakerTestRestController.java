package io.lhysin.echo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class CircuitBreakerTestRestController {

    private final AtomicBoolean success = new AtomicBoolean(true);
    private final Random random = new Random();

    @Value("${test.delay.slow:3500}")
    private long slowDelayMs;

    @Value("${test.delay.slowRate:0.3}")
    private double slowRate;

    @Value("${test.delay.failRate:0.3}")
    private double failRate;

    @Scheduled(fixedRateString = "${test.toggle.interval:5000}")
    public void toggleStatus() {
        success.set(!success.get());
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return success.get()
            ? ResponseEntity.ok("Success")
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure");
    }

    @GetMapping("/delay")
    public ResponseEntity<String> delay() throws InterruptedException {
        if(success.get()) {
            return ResponseEntity.ok("Fast Response");
        } else {
            Thread.sleep(slowDelayMs);
            return ResponseEntity.ok("Slow Response");
        }
    }

    @GetMapping("/unstable")
    public ResponseEntity<String> unstable() throws InterruptedException {
        double roll = random.nextDouble();

        if (roll < failRate) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("unstable fail");
        } else if (roll < failRate + slowRate) {
            Thread.sleep(slowDelayMs);
            return ResponseEntity.ok("unstable slow");
        } else {
            return ResponseEntity.ok("unstable success");
        }
    }

}