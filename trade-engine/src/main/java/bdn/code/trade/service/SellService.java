package bdn.code.trade.service;

import bdn.code.trade.exception.TradeException;
import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.Client;
import bdn.code.trade.model.ClientOrder;
import bdn.code.trade.model.Product;
import bdn.code.trade.model.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SellService implements OrderService {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;

    @Override
    public TradeOrder processOrder(ClientOrder clientOrder, Client client, List<Product> productList) {

        if (!clientHasEnoughQuantity(client.getQuantity(), clientOrder.getQuantity())) {

            throw new TradeException(ApiMessages.CLIENT_HAS_NOT_ENOUGH_QUANTITY.getMessage());
        }

        int totalQuantity = clientOrder.getQuantity();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        Set<Long> modifiedProductId = new HashSet<>();

        for (Product p : productList) {

            int limit = 5;
            while (totalQuantity > 0 && limit > 0) {
                p.setQuantity(p.getQuantity()+1);
                totalPrice = totalPrice.add(p.getPrice());
                modifiedProductId.add(p.getId());
                totalQuantity--;
                limit--;
            }
        }

        if (totalQuantity != 0) {

            throw new TradeException(ApiMessages.PRODUCT_HAS_NOT_ENOUGH_QUANTITY.getMessage());
        }

        int quantity = client.getQuantity() - clientOrder.getQuantity();
        BigDecimal amount = client.getAmount().add(totalPrice);
        boolean updateClient = clientService.updateClient(client, amount, quantity);
        if (!updateClient) {

            throw new TradeException(ApiMessages.CLIENT_NOT_UPDATED.getMessage());
        }

        productList.removeIf(p -> !modifiedProductId.contains(p.getId()));
        boolean updateProducts = productService.updateProducts(productList);
        if (!updateProducts) {

            throw new TradeException(ApiMessages.PRODUCT_NOT_UPDATED.getMessage());
        }

        return new TradeOrder.Builder()
                .setClientName(client.getName())
                .setCurrency(client.getCurrency())
                .setTotalPrice(totalPrice)
                .setClientOrder(clientOrder)
                .build();
    }

    private boolean clientHasEnoughQuantity(int clientQuantity, int orderQuantity) {

        return clientQuantity >= orderQuantity;
    }
}
