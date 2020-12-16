package bdn.code.trade.service;

import bdn.code.trade.model.Client;
import bdn.code.trade.model.ClientOrder;
import bdn.code.trade.model.Product;
import bdn.code.trade.model.TradeOrder;

import java.util.List;

public interface OrderService {

    TradeOrder processOrder(ClientOrder clientOrder, Client client, List<Product> productList);
}
