package bdn.code.trade.controller;

import bdn.code.trade.exception.NotFoundException;
import bdn.code.trade.exception.TradeException;
import bdn.code.trade.message.ApiMessages;
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

        Client client = null;
        try {

            client = webClientBuilder.baseUrl(CLIENT_SERVICE_URL)
                    .build()
                    .get()
                    .uri(String.valueOf(id))
                    .retrieve()
                    .bodyToMono(Client.class)
                    .block();
        } catch (Exception e) {

            throw new NotFoundException(ApiMessages.CLIENT_NOT_FOUND.getMessage());
        }
        return client;
    }

    public boolean updateClient(Client client) {

        boolean updated = false;
        try {

            updated = Objects.requireNonNull(webClientBuilder
                    .baseUrl(CLIENT_SERVICE_URL)
                    .build()
                    .put()
                    .uri(String.valueOf(client.getId()))
                    .body(Mono.just(client), Client.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block())
                    .getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {

            throw new TradeException(ApiMessages.CLIENT_NOT_UPDATED.getMessage());
        }
        return updated;
    }
}
