package com.hospital.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Api api = new Api();
    private Cors cors = new Cors();

    @Getter
    @Setter
    public static class Api {
        private String host;
        private int port;
        private String baseUrl;
        private Endpoints endpoints = new Endpoints();
    }

    @Getter
    @Setter
    public static class Endpoints {
        private String menus;
        private String staff;
    }

    @Getter
    @Setter
    public static class Cors {
        private List<String> allowedOriginPatterns = new ArrayList<>();
    }
}
