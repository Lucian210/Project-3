package com.involveininnovation.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.involveininnovation.chat.entities.Client;

import java.util.List;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientById(Long id);
    Client findClientByName(String name);
    List<Client> findAll();
}
