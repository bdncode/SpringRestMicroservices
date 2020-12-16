package bdn.code.trade.controller;

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

    public List<Product> getProducts(TradeType tradeType, Currency currency, Market market) {

        String tradeOptions = tradeType + "/" + market + "/" + currency;

        return Objects.requireNonNull(webClientBuilder.baseUrl(PRODUCT_URL)
                .build()
                .get()
                .uri(tradeOptions)
                .retrieve()
                .bodyToMono(ProductList.class)
                .block())
                .getProducts();
    }

    public boolean updateProduct(Product product) {

        return Objects.equals(Objects.requireNonNull(webClientBuilder.baseUrl(PRODUCT_URL)
                .build()
                .put()
                .uri(String.valueOf(product.getId()))
                .body(Mono.just(product), Product.class)
                .retrieve()
                .toEntity(String.class)
                .block())
                .getStatusCode(), HttpStatus.OK);
    }
}
