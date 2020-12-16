package bdn.code.client.service;

import bdn.code.client.exception.NotFoundException;
import bdn.code.client.message.ApiMessages;
import bdn.code.client.model.Client;
import bdn.code.client.repository.ClientDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static bdn.code.client.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientDao clientDao;
    @InjectMocks
    ClientService clientServiceTest;
    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;

    long id = 32;
    Client testClient = new Client.Builder()
                    .setId(id)
                    .setName("John Doe")
                    .setAmount(BigDecimal.valueOf(8700.92))
                    .setQuantity(4)
                    .setCurrency(USD)
                    .build();
    BigDecimal updatedAmount = BigDecimal.valueOf(83);
    int updatedQuantity = 0;

    @Test
    void fetchClient() {

        when(clientDao.findById(anyLong()))
                .thenReturn(Optional.of(testClient));
        Client client = clientServiceTest.fetchClient(id);

        assertEquals(testClient,client);
    }

    @Test
    void fetchClientThrowNotFoundException() {

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> clientServiceTest.fetchClient(id));

        assertEquals(String.format(
                ApiMessages.NOT_FOUND.getMessage(),id),
                notFoundException.getMessage());
    }

    @Test
    void createClient() {

        given(clientDao.save(testClient))
                .willReturn(testClient);

        long createdId = clientServiceTest.createClient(testClient);
        then(clientDao).should()
                .save(clientArgumentCaptor.capture());

        assertEquals(createdId,clientArgumentCaptor.getValue().getId());
    }

    @Test
    void updateClient() {

        given(clientDao.findById(anyLong()))
                .willReturn(Optional.of(testClient));

        Client updatedTestClient = new Client.Builder()
                .setId(testClient.getId())
                .setName(testClient.getName())
                .setAmount(updatedAmount)
                .setQuantity(updatedQuantity)
                .setCurrency(testClient.getCurrency())
                .build();

        when(clientDao.save(updatedTestClient))
                .thenReturn(updatedTestClient);

        clientServiceTest.updateClient(testClient);

        then(clientDao).should()
                .save(clientArgumentCaptor.capture());

        assertEquals(updatedTestClient.getId(),clientArgumentCaptor.getValue().getId());
        assertEquals(updatedTestClient.getAmount(),clientArgumentCaptor.getValue().getAmount());
        assertEquals(updatedTestClient.getQuantity(),clientArgumentCaptor.getValue().getQuantity());
    }
}