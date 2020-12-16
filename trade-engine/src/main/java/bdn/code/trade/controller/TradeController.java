package bdn.code.trade.controller;

import bdn.code.trade.exception.NotFoundException;
import bdn.code.trade.exception.TradeException;
import bdn.code.trade.model.ClientOrder;
import bdn.code.trade.model.TradeOrder;
import bdn.code.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TradeController.BASE_URL)
public class TradeController {

    protected static final String BASE_URL = "/api/v1/order";
    protected static final String CREATE_TRADE_ORDER = "/new";
    @Autowired
    TradeService tradeService;

    @PostMapping(value = CREATE_TRADE_ORDER)
    public ResponseEntity<TradeOrder> createTradeOrder(@RequestBody ClientOrder clientOrder) throws Exception {

        TradeOrder tradeOrder;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {

            tradeOrder = tradeService.createTradeOrder(clientOrder);
        } catch (NotFoundException notFoundException) {

            throw new NotFoundException(notFoundException.getMessage());
        } catch (TradeException tradeException) {

            throw new TradeException(tradeException.getMessage());
        } catch (Exception defaultException) {

            throw new Exception(defaultException.getMessage());
        }
        return new ResponseEntity<>(tradeOrder ,headers, HttpStatus.OK);
    }
}
