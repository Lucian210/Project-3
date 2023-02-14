package ro.tuc.ds2020.services;


import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.entities.Client;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface ClientService {
    List<Client> findAllClients();
    Client findClientById(Long id);
    Client findClientByName(String name);
    Client updateClient(Client client, String name, String adresa);
    Client updateClient(ClientDTO clientDTO);
    Client logoutClient(Long id, Timestamp ts);
    Client addClient(Long id, String name, String adresa, String birthdate);
    List<Client> deleteClient(Long id);
}
