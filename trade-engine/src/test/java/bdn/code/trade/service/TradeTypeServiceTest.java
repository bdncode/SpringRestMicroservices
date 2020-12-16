package bdn.code.trade.service;

import bdn.code.trade.model.TradeOrder;
import bdn.code.trade.repository.TradeDao;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TradeTypeServiceTest {

//    Define the @Mock dependant classes
//    @InjectMocks in the class to test
//    Assign default response value with 'when' in the test method
//    Initialize the mock classes @BeforeEach test with MockitoAnnotations.initMocks(this);

    @Mock
    ClientService clientService;
    @Mock
    ProductService productService;
    @Mock
    TradeDao tradeDao;
    @Captor
    private ArgumentCaptor<TradeOrder> tradeOrderArgumentCaptor;
    @InjectMocks
    TradeService tradeServiceTest;

    @BeforeEach
    void setUpMocksForTradeServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    BigDecimal quantity = BigDecimal.valueOf(1.34);

//    Client testClient = new Client(
//            32,
//            "John Doe",
//            BigDecimal.valueOf(8700.92),
//            "USD");
//
//    Price testPrice = new Price(
//            3,
//            Trade.BUY,
//            Market.TORONTO,
//            "USD",
//            BigDecimal.valueOf(0.026),
//            60640);
//
//    ClientOrder testBuyTrade = new ClientOrder(
//            1,
//            testClient.getId(),
//            Trade.BUY,
//            Market.TORONTO,
//            "USD",
//            quantity
//    );
//
//    @Test
//    void testPostBuy() {
//        when(clientService.getClient(testClient.getId())).thenReturn(testClient);
//        when(priceService.getPrices(
//                testBuyTrade.getTrade(),
//                testBuyTrade.getCurrency(),
//                testBuyTrade.getMarket())).thenReturn(testPrice);
//        tradeServiceTest.postBuy(testBuyTrade);
////        TradeOrder tradeOrder = tradeOrderArgumentCaptor.capture();
////        System.out.println(tradeOrder);
////        then(tradeDao).should().save(tradeOrder);
//    }
//
//    @Test
//    void testPostBuyThrowsExceptionWithNullClient() {
//        when(clientService.getClient(testClient.getId())).thenReturn(null);
//        when(priceService.getPrices(
//                testBuyTrade.getTrade(),
//                testBuyTrade.getCurrency(),
//                testBuyTrade.getMarket())).thenReturn(testPrice);
//
//        assertThrows(RuntimeException.class,
//                () -> {tradeServiceTest.postBuy(testBuyTrade);});
//    }
//
//    @Test
//    void testPostBuyThrowsExceptionWithNullprice() {
//        when(clientService.getClient(testClient.getId())).thenReturn(testClient);
//        when(priceService.getPrices(
//                testBuyTrade.getTrade(),
//                testBuyTrade.getCurrency(),
//                testBuyTrade.getMarket())).thenReturn(null);
//
//        assertThrows(RuntimeException.class,
//                () -> {tradeServiceTest.postBuy(testBuyTrade);});
//    }
//
//    ClientOrder sellTrade = testBuyTrade;
//    @Test
//    void testPostSell() {
//    }
}