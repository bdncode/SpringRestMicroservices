package bdn.code.product.repository;

import bdn.code.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends CrudRepository<Product, Long> {

    String query = "SELECT * FROM market.product WHERE trade = ?1 AND market = ?2 AND currency = ?3";

    @Query(value = query, nativeQuery = true)
    Optional<List<Product>> findByTradeAndMarketAndCurrency(String trade, String market, String currency);
}
