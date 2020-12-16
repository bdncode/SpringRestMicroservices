package bdn.code.product.controller;

import bdn.code.product.message.ApiMessages;
import bdn.code.product.model.*;
import bdn.code.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ProductController.BASE_URL)
public class ProductController {

    protected static final String BASE_URL = "/api/v1/product";
    protected static final String FETCH_ALL_PRODUCTS = "/";
    protected static final String FETCH_PRODUCTS = "/{tradeType}/{market}/{currency}";
    protected static final String UPDATE_PRODUCT = "/{id}";
    @Autowired
    ProductService productService;

    @GetMapping(FETCH_ALL_PRODUCTS)
    public ResponseEntity<ProductList> fetchAllProducts() {

        List<Product> products;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            products = productService.fetchAllProducts();
        } catch (Exception e) {
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductList(products), headers, HttpStatus.OK);
    }

    @GetMapping(FETCH_PRODUCTS)
    public ResponseEntity<ProductList> fetchProducts(@PathVariable TradeType tradeType,
                                   @PathVariable Market market,
                                   @PathVariable Currency currency) {

        List<Product> products;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            products = productService.fetchProducts(tradeType, market, currency);
        } catch (Exception e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductList(products),headers, HttpStatus.OK);
    }

    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<String> updateProduct(@RequestBody Product requestProduct) {

        long id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        try {
            id = productService.updateProduct(requestProduct);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiMessages.INTERNAL_SERVER_ERROR.getMessage(),
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                String.format(ApiMessages.UPDATED.getMessage(),id),
                headers,
                HttpStatus.OK);
    }
}
