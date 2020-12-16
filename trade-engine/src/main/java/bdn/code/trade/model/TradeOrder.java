package bdn.code.trade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Tradeorder")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TradeOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    protected long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    @NotNull
    private ClientOrder clientOrder;
    @Column(name = "clientname")
    protected String clientName;
    @Enumerated(EnumType.ORDINAL)
    protected Currency currency;
    @Column(name = "totalprice")
    protected BigDecimal totalPrice;
    @Column(name = "storedindb")
    private Date storedInDb = new Date();


    public TradeOrder(Builder builder) {
        this.clientOrder = builder.clientOrder;
        this.clientName = builder.clientName;
        this.currency = builder.currency;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {

        public ClientOrder clientOrder;
        public String clientName;
        public Currency currency;
        public BigDecimal totalPrice;

        public Builder setClientOrder(ClientOrder clientOrder) {
            this.clientOrder = clientOrder;
            return this;
        }

        public Builder setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public TradeOrder build() {
            return new TradeOrder(this);
        }
    }
}