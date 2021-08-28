package com.egs.shopping.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private final CorsConfiguration cors = new CorsConfiguration();
    private final Security security = new Security();

    @NoArgsConstructor
    @Getter
    public static class Security {
        private final Authentication authentication = new Authentication();

        @NoArgsConstructor
        @Getter
        public static class Authentication {
            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            public static class Jwt {
                private String secret;
                private String base64Secret;
                private long tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe;

                public Jwt() {
                    this.secret = "";
                    this.base64Secret = "";
                    this.tokenValidityInSeconds = 3600L;
                    this.tokenValidityInSecondsForRememberMe = 864000L;
                }
            }
        }
    }
}

