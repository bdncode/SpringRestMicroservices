package bdn.code.trade.service;

import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.Client;
import bdn.code.trade.model.ClientOrder;
import bdn.code.trade.model.Product;
import bdn.code.trade.model.TradeOrder;
import bdn.code.trade.repository.TradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BuyService implements OrderService {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    private TradeDao tradeDao;

    public TradeOrder processOrder(ClientOrder clientOrder, Client client, List<Product> productList) {

        int remainingQuantity = clientOrder.getQuantity();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        Set<Long> modifiedProductId = new HashSet<>();

        for (Product p : productList) {

            while (remainingQuantity > 0 && p.getQuantity() > 0) {
                p.setQuantity(p.getQuantity()-1);
                totalPrice = totalPrice.add(p.getPrice());
                modifiedProductId.add(p.getId());
                remainingQuantity--;
            }
        }

        if (!clientHasEnoughAmount(client.getAmount(), totalPrice)) {

            throw new RuntimeException(ApiMessages.CLIENT_HAS_NOT_ENOUGH_FUNDS.getMessage());
        }

        int quantity = client.getQuantity() + clientOrder.getQuantity();
        BigDecimal amount = client.getAmount().subtract(totalPrice);
        boolean updateClient = clientService.updateClient(client, amount, quantity);
        if (!updateClient) {

            throw new RuntimeException(ApiMessages.CLIENT_NOT_UPDATED.getMessage());
        }

        productList.removeIf(p -> !modifiedProductId.contains(p.getId()));
        boolean updateProducts = productService.updateProducts(productList);
        if (!updateProducts) {

            throw new RuntimeException(ApiMessages.PRODUCT_NOT_UPDATED.getMessage());
        }

        return new TradeOrder.Builder()
                .setClientName(client.getName())
                .setCurrency(client.getCurrency())
                .setTotalPrice(totalPrice)
                .setClientOrder(clientOrder)
                .build();
    }

    private boolean clientHasEnoughAmount(BigDecimal clientAmount, BigDecimal price) {

        return clientAmount.compareTo(price) > -1;
    }
}
