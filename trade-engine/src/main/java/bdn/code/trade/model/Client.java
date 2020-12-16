package bdn.code.trade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    protected long id;
    protected String name;
    protected BigDecimal amount;
    protected int quantity;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    protected Currency currency;

    public Client(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.amount = builder.amount;
        this.quantity = builder.quantity;
        this.currency = builder.currency;
    }

    public static class Builder {

        private long id;
        private String name;
        private BigDecimal amount;
        private int quantity;
        private Currency currency;

        public Builder setId(long id) {

            this.id = id;
            return this;
        }

        public Builder setName(String name) {

            this.name = name;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {

            this.amount = amount;
            return this;
        }

        public Builder setQuantity(int quantity) {

            this.quantity = quantity;
            return this;
        }

        public Builder setCurrency(Currency currency) {

            this.currency = currency;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}