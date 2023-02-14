package ro.tuc.ds2020.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.ClientService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;

    public ClientServiceImpl(ClientRepository clientRepository, SensorRepository sensorRepository, DeviceRepository deviceRepository) {

        this.clientRepository = clientRepository;
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(Long id){
        try{
            return clientRepository.findClientById(id);
        }
        catch (Exception exception){
            System.out.println("Clientul nu a fost gasit");
        }
        return null;
    }


    @Override
    public Client findClientByName(String name) {
        return clientRepository.findClientByName(name);
    }

    @Override
    @Transactional
    public Client updateClient(Client client, String name, String adresa) {
        Client client1 = clientRepository.findClientById(client.getId());
        client1.setName(name);
        client1.setAddress(adresa);
        return client1;
    }

    @Override
    @Transactional
    public Client updateClient(ClientDTO clientDTO) {
        Client client1 = clientRepository.findClientById(clientDTO.getId());
        if(! clientDTO.getName().equals("")){
            client1.setName(clientDTO.getName());
        }
        if(! clientDTO.getAddress().equals("")){
            client1.setAddress(clientDTO.getAddress());
        }
        if(! clientDTO.getBirthDate().equals("")){
            client1.setBirthDate(clientDTO.getBirthDate());
        }
        clientRepository.save(client1);
        return client1;
    }

    @Override
    public Client logoutClient(Long id, Timestamp ts) {
        Client client1 = clientRepository.findClientById(id);
        client1.setLogat(false);
        client1.setTimestamp(ts);
        clientRepository.save(client1);

        return client1;
    }



    @Override
    public Client addClient(Long id, String name, String adresa, String birthdate) {
        Client client = new Client(id, name, adresa, birthdate);
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> deleteClient(Long id) {
        List<Device> allDevices = deviceRepository.findAll();
        for(int i = 0; i < allDevices.size(); i++){
            if (allDevices.get(i).getClient().getId() == id){
                Long currentDeviceId = allDevices.get(i).getId();

                List<Sensor> allSensors = sensorRepository.findAll();
                for(int j = 0; j < allSensors.size(); j++){
                    if (allSensors.get(j).getDevice().getId() == currentDeviceId){
                        Long currentSensorId = allSensors.get(j).getId();
                        sensorRepository.deleteById(currentSensorId);
                    }
                }

                deviceRepository.deleteById(currentDeviceId);
            }
        }
        clientRepository.deleteById(id);
        return clientRepository.findAll();
    }
}
