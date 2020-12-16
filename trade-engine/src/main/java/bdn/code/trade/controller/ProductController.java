package bdn.code.trade.controller;

import bdn.code.trade.exception.NotFoundException;
import bdn.code.trade.exception.TradeException;
import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String PRODUCT_URL = "http://localhost:8081/api/v1/product/";

    public List<Product> getProducts(TradeType tradeType, Market market, Currency currency) {

        String tradeOptions = String.format("%s/%s/%s", tradeType, market, currency);

        List<Product> productList = null;
        try {
            productList = Objects.requireNonNull(webClientBuilder.baseUrl(PRODUCT_URL)
                    .build()
                    .get()
                    .uri(tradeOptions)
                    .retrieve()
                    .bodyToMono(ProductList.class)
                    .block())
                    .getProducts();
        } catch (Exception e) {

            throw new NotFoundException(ApiMessages.PRODUCT_NOT_FOUND.getMessage());
        }
        return productList;
    }

    public boolean updateProduct(Product product) {

        boolean updated;
        try {

            updated = Objects.requireNonNull(webClientBuilder.baseUrl(PRODUCT_URL)
                    .build()
                    .put()
                    .uri(String.valueOf(product.getId()))
                    .body(Mono.just(product), Product.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block())
                    .getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {

            throw new TradeException(ApiMessages.PRODUCT_NOT_UPDATED.getMessage());
        }
        return updated;
    }
}
