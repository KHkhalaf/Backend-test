package com.backend.quiz.services;

import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.Client;
import com.backend.quiz.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    public ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> listAll(){
        return clientRepository.findAll();
    }

    public void save(Client client) {

        clientRepository.save(client);
    }

    public void editClient(Client client) throws ResourceNotFoundException {
        Client _client = get(client.getId());
        _client.setFirstName(client.getFirstName());
        _client.setLastName(client.getLastName());
        _client.setPhoneNumber(client.getPhoneNumber());

        clientRepository.save(_client);
    }
    public Client get(long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
    }
}
