package bdn.code.trade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    protected long id;
    protected TradeType tradeType;
    protected Market market;
    protected Currency currency;
    protected int quantity;
    protected BigDecimal price;
}
