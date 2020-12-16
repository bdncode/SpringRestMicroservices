package bdn.code.client.controller;

import bdn.code.client.exception.NotFoundException;
import bdn.code.client.model.Client;
import bdn.code.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static bdn.code.client.model.Currency.USD;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientControllerTest;
    @Captor
    ArgumentCaptor<Client> clientArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;
    @Captor
    ArgumentCaptor<BigDecimal> amountArgumentCaptor;
    @Captor
    ArgumentCaptor<Integer> quantityArgumentCaptor;

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

        when(clientService.fetchClient(anyLong()))
                .thenReturn(testClient);

        ResponseEntity<Client> clientResponseEntity = clientControllerTest.fetchClient(id);
        assertEquals(testClient,clientResponseEntity.getBody());
        assertEquals(HttpStatus.OK,clientResponseEntity.getStatusCode());
    }

    @Test
    void fetchClientThrowNotFoundException() {

        when(clientService.fetchClient(anyLong())).
                thenThrow(new NotFoundException(anyString()));

        ResponseEntity<Client> clientResponseEntity = clientControllerTest.fetchClient(id);
        assertNull(clientResponseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND,clientResponseEntity.getStatusCode());
    }

    @Test
    void createClient() {

        clientControllerTest.createClient(testClient);
        then(clientService).should()
                .createClient(clientArgumentCaptor.capture());

        assertEquals(clientArgumentCaptor.getValue(),testClient);
    }

    @Test
    void updateClient() {

        clientControllerTest.updateClient(testClient);
        then(clientService).should()
                .updateClient(clientArgumentCaptor.capture());

        assertEquals(testClient,clientArgumentCaptor.getValue());
    }
}