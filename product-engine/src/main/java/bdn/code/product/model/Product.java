package bdn.code.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    protected long id;
    @Column(name = "trade")
    @Enumerated(EnumType.STRING)
    protected TradeType tradeType;
    @Enumerated(EnumType.STRING)
    protected Market market;
    @Enumerated(EnumType.STRING)
    protected Currency currency;
    protected int quantity;
    protected BigDecimal price;
}
