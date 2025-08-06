package io.lhysin.echo.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class ApiCallCounter {
    private final AtomicLong counter = new AtomicLong();

    public long incrementAndGet() {
        while (true) {
            long current = counter.get();

            // 오버플로우 방지 로직
            if (current >= Long.MAX_VALUE - 9999) {
                // 초기화 시도
                if (counter.compareAndSet(current, 0)) {
                    return 0;
                }
                continue; // 초기화 실패했으면 다시 시도
            }

            long next = current + 1;
            if (counter.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}