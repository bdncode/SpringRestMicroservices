package bdn.code.trade.repository;

import bdn.code.trade.model.ClientOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderDao extends CrudRepository<ClientOrder, Long> {
}
