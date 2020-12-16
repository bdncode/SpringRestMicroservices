package bdn.code.client.controller;

import bdn.code.client.message.ApiMessages;
import bdn.code.client.model.Client;
import bdn.code.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = ClientController.BASE_URL)
public class ClientController {

    protected static final String BASE_URL = "/api/v1/client";
    protected static final String FETCH_CLIENT = "/{id}";
    protected static final String CREATE_CLIENT = "/new";
    protected static final String UPDATE_CLIENT = "/{id}";
    @Autowired
    ClientService clientService;

    @GetMapping(FETCH_CLIENT)
    public ResponseEntity<Client> fetchClient(@PathVariable long id) {

        Client client;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            client = clientService.fetchClient(id);
        } catch (Exception e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client,headers,HttpStatus.OK);
    }

    @PostMapping(CREATE_CLIENT)
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createClient(@RequestBody Client requestClient) {

        long id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        try {
            id = clientService.createClient(requestClient);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiMessages.INTERNAL_SERVER_ERROR.getMessage(),
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                String.format(ApiMessages.CREATED.getMessage(),id),
                headers,
                HttpStatus.CREATED);
    }

    @PutMapping(UPDATE_CLIENT)
    public ResponseEntity<String> updateClient(@RequestBody Client requestClient) {

        long id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        try {
            id = clientService.updateClient(requestClient);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiMessages.INTERNAL_SERVER_ERROR.getMessage(),
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                String.format(ApiMessages.UPDATED.getMessage(),id),
                headers,
                HttpStatus.OK);
    }
}
