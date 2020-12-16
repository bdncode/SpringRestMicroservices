package bdn.code.product.service;

import bdn.code.product.exception.NotFoundException;
import bdn.code.product.message.ApiMessages;
import bdn.code.product.model.Currency;
import bdn.code.product.model.Market;
import bdn.code.product.model.Product;
import bdn.code.product.model.TradeType;
import bdn.code.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public List<Product> fetchAllProducts() {

        return (List<Product>) productDao.findAll();
    }

    public List<Product> fetchProducts(TradeType tradeType, Market market, Currency currency) {

        return productDao.findByTradeAndMarketAndCurrency(tradeType.toString(),market.toString(), currency.toString())
                .orElseThrow(() -> new NotFoundException(String.format(ApiMessages.
                        NOT_FOUND.getMessage(),market.toString())));
    }

    public long updateProduct(Product requestProduct) {

        return productDao.findById(requestProduct.getId())
                .map(p -> {
                    p.setQuantity(requestProduct.getQuantity());
                    return productDao.save(p).getId();
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format(ApiMessages.NOT_FOUND.getMessage(), requestProduct.getId())));
    }
}
