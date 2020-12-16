package bdn.code.trade.service;

import bdn.code.trade.exception.TradeException;
import bdn.code.trade.message.ApiMessages;
import bdn.code.trade.model.*;
import bdn.code.trade.repository.ClientOrderDao;
import bdn.code.trade.repository.TradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Client client = clientService.getClient(clientOrder.getClientId());

        TradeType tradeType = clientOrder.getTradeType();

        List<Product> productList = productService.getProducts(
                tradeType,
                clientOrder.getMarket(),
                client.getCurrency());

        TradeOrder tradeOrder;

        if (tradeType == TradeType.BUY) {

            tradeOrder = buyService.processOrder(clientOrder, client, productList);
        } else if (tradeType == TradeType.SELL) {

            tradeOrder = sellService.processOrder(clientOrder, client, productList);
        } else {

            throw new TradeException(ApiMessages.UNKNOWN_TRADE.getMessage());
        }

        return tradeDao.save(tradeOrder);
    }
}
