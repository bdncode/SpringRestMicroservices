package bdn.code.trade.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBuilder {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
