package bdn.code.trade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Clientorder")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    protected long id;
    @Column(name = "clientid")
    protected long clientId;
    @Column(name = "orderuuid")
    protected String orderUuid = UUID.randomUUID().toString();
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tradetype")
    protected TradeType tradeType;
    @Enumerated(EnumType.ORDINAL)
    protected Market market;
    protected int quantity;
    @Column(name = "storedindb")
    private Date storedInDb = new Date();
    @OneToOne(mappedBy = "clientOrder")
    private TradeOrder tradeOrder;
}