package bdn.code.trade.service;

import bdn.code.trade.controller.ProductController;
import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.Currency;
import bdn.code.trade.model.Market;
import bdn.code.trade.model.Product;
import bdn.code.trade.model.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductController productController;

    public List<Product> getProducts(TradeType tradeType, Currency currency, Market market) {

        List<Product> productList = productController.getProducts(tradeType, currency, market);

        if (tradeType == TradeType.BUY) {

            return productList.stream()
                    .filter(p -> p.getQuantity() != 0)
                    .sorted(Comparator.comparing(Product::getPrice))
                    .collect(Collectors.toList());
        } else if (tradeType == TradeType.SELL) {

            return productList.stream()
                    .sorted(Comparator.comparing(Product::getPrice).reversed())
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException(String.format(ApiMessages.UNKNOWN_TRADE.getMessage(), tradeType.name()));
        }
    }

    public boolean updateProducts(List<Product> productList) {

        return productList.stream()
                .map(product -> productController.updateProduct(product))
                .filter(b -> !b)
                .findAny()
                .isEmpty();
    }
}
