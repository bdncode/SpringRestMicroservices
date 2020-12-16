package bdn.code.trade.controller;

import bdn.code.trade.model.Client;
import bdn.code.trade.model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class ClientController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String CLIENT_SERVICE_URL = "http://localhost:8084/api/v1/client/";

    public Client getClient(long id) {

        return webClientBuilder.baseUrl(CLIENT_SERVICE_URL)
                .build()
                .get()
                .uri(String.valueOf(id))
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    public boolean updateClient(Client client) {

        return Objects.equals(Objects.requireNonNull(webClientBuilder.baseUrl(CLIENT_SERVICE_URL)
                .build()
                .put()
                .uri(String.valueOf(client.getId()))
                .body(Mono.just(client), Client.class)
                .retrieve()
                .toEntity(String.class)
                .block())
                .getStatusCode(), HttpStatus.OK);
    }
}
