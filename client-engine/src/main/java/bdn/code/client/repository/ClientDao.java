package bdn.code.client.repository;

import bdn.code.client.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {
}
