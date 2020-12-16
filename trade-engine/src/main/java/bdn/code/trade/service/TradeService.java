package bdn.code.trade.service;

import bdn.code.trade.exception.NotFoundException;
import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.*;
import bdn.code.trade.repository.ClientOrderDao;
import bdn.code.trade.repository.TradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    BuyService buyService;
    @Autowired
    SellService sellService;
    @Autowired
    TradeDao tradeDao;
    @Autowired
    ClientOrderDao clientOrderDao;

    public TradeOrder createTradeOrder(ClientOrder clientOrder) {

        clientOrderDao.save(clientOrder);

        Client client = Optional.of(clientService.getClient(clientOrder.getClientId()))
                .orElseThrow(() -> new NotFoundException(String.format(
                        ApiMessages.CLIENT_NOT_FOUND.getMessage(),clientOrder.getClientId())));

        List<Product> productList = Optional.of(productService.getProducts(
                clientOrder.getTradeType(),
                client.getCurrency(),
                clientOrder.getMarket()))
                .orElseThrow(() -> new NotFoundException(String.format(
                        ApiMessages.PRODUCT_NOT_FOUND.getMessage(),clientOrder.getMarket())));

        TradeOrder tradeOrder;

        if (clientOrder.getTradeType() == TradeType.BUY) {

            tradeOrder = buyService.processOrder(clientOrder, client, productList);
        } else if (clientOrder.getTradeType() == TradeType.SELL) {

            tradeOrder = sellService.processOrder(clientOrder, client, productList);
        } else {

            throw new RuntimeException(ApiMessages.INTERNAL_SERVER_ERROR.getMessage());
        }

        return tradeDao.save(tradeOrder);
    }
}
