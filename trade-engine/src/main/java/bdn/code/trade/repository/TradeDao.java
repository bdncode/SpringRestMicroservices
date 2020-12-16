package bdn.code.trade.repository;

import bdn.code.trade.model.TradeOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDao extends CrudRepository<TradeOrder, Long> {
}
