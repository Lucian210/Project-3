package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Client;

import java.util.List;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientById(Long id);
    Client findClientByName(String name);
    List<Client> findAll();
}
