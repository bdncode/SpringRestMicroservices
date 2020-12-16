package bdn.code.trade.controller;

import bdn.code.trade.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradeTypeControllerTest {

    /*
    Todo

    Add custom exceptions
    Add enum in db
    Improve trade logic price/quantity
    Use WebClient for asynchronous calls
    * */

    @Mock
    WebClient webClient;
    @Mock
    TradeService tradeServiceTest;
    @InjectMocks
    TradeController tradeControllerTest;
    @BeforeEach
    void setUpMocksForTradeControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

//    Client testClient = new Client(
//            32,
//            "John Doe",
//            BigDecimal.valueOf(8700.92),
//            "USD");
//    BigDecimal quantity = BigDecimal.valueOf(1.34);
//
//    ClientOrder buyTrade = new ClientOrder(
//            1,
//            1,
//            Trade.BUY,
//            Market.TORONTO,
//            "USD",
//            quantity
//    );
//
//    @Test
//    public void postBuy() {
//        when(webClient.get().uri("AnyString()")
//                .retrieve()
//                .bodyToMono(Client.class)
//                .block())
//                .thenReturn(testClient);
//        tradeControllerTest.postBuy(buyTrade);
//    }
////    when post a buy
////    set price
////    get client data from id
////    check client has money
////    get lower price from market, with currency
////    set quantity x money, market, currency
////    post to buy service to store in db
////    post to update client money
//
//    ClientOrder sellTrade = buyTrade;
//
//    @Test
//    public void postSell() {
//        sellTrade.setTrade(Trade.SELL);
//        tradeControllerTest.postSell(sellTrade);
//    }
//    when post a sell
//    set quantity
//    get client data from id
//    check client has quantity
//    get lower price from market, with currency
//    set quantity x money, market, currency
//    post to sell service to store in db
//    post to update client quantity
}