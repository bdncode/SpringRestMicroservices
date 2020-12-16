package bdn.code.client.service;

import bdn.code.client.exception.NotFoundException;
import bdn.code.client.message.ApiMessages;
import bdn.code.client.model.Client;
import bdn.code.client.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    public Client fetchClient(long id) {

        return clientDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ApiMessages.
                        NOT_FOUND.getMessage(),id)));
    }

    public long createClient(Client client) {

        return clientDao.save(client).getId();
    }

    public long updateClient(Client requestClient) {

        return clientDao.findById(requestClient.getId())
                .map(c -> {
                    c.setAmount(requestClient.getAmount());
                    c.setQuantity(requestClient.getQuantity());
                    return clientDao.save(c).getId();
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format(ApiMessages.NOT_FOUND.getMessage(), requestClient.getId())));
    }
}