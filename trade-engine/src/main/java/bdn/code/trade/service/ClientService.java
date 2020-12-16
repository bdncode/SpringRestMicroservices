package bdn.code.trade.service;

import bdn.code.trade.model.Client;
import bdn.code.trade.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClientService {

    @Autowired
    ClientController clientController;

    public Client getClient(long clientId) {
        return clientController.getClient(clientId);
    }

    public boolean updateClient(Client client, BigDecimal amount, int quantity) {

        client.setAmount(amount);
        client.setQuantity(quantity);
        return clientController.updateClient(client);
    }
}
