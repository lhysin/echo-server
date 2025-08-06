package io.lhysin.echo.model;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;

public record FallbackResponse(
    long count,
    String method,
    String requestUri,
    String queryString,
    String clientIp,
    String timestamp
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long count;
        private String method;
        private String requestUri;
        private String queryString;
        private String clientIp;
        private String timestamp;

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public Builder fromHttpRequest(HttpServletRequest request) {
            this.requestUri = request.getRequestURI();
            this.method = request.getMethod();
            this.queryString = request.getQueryString();
            this.clientIp = request.getRemoteAddr();
            this.timestamp = Instant.now().toString();
            return this;
        }

        public FallbackResponse build() {
            return new FallbackResponse(
                count,
                method,
                requestUri,
                queryString,
                clientIp,
                timestamp
            );
        }
    }
}
