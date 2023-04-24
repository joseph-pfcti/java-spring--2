package com.pfcti.demo2.service;

import com.pfcti.demo2.dto.ClientDto;
import com.pfcti.demo2.model.Client;
import com.pfcti.demo2.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    private AddressRepository addressRepository;
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private InvestmentRepository investmentRepository;

    public void insert(ClientDto clientDto) {
        Client client = new Client();

        client.setName(clientDto.getName());
        client.setLastNames(clientDto.getLastNames());
        client.setDni(clientDto.getDni());
        client.setPhone(clientDto.getPhone());

        clientRepository.save(client);
    }

    public Client find(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> {
            throw new RuntimeException("Client doesn't exist");
        });

        return client;
    }

    public void update(ClientDto clientDto) {
        Client client = new Client();

        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setLastNames(clientDto.getLastNames());
        client.setDni(clientDto.getDni());
        client.setPhone(clientDto.getPhone());

        clientRepository.save(client);
    }

    public void delete(int clientId) {
        addressRepository.deleteAllByClient_Id(clientId);
        investmentRepository.deleteAllByClient_Id(clientId);
        cardRepository.deleteAllByClient_Id(clientId);
        accountRepository.deleteAllByClient_Id(clientId);

        clientRepository.deleteById(clientId);
    }

    public List<ClientDto> getClientsByCountryAndActiveAccounts(String countryCode) {
        List<ClientDto> dtoClients = new ArrayList<>();

        List<Client> clients = clientRepository.findClientsByCountryAndAccounts_StateIsTrue(countryCode);

        clients.forEach(client -> {
            dtoClients.add(this.createClientDto(client));
        });

        return dtoClients;
    }

    public List<ClientDto> findClientByCountryAndCardsStateIsFalse (String countryCode) {

        List<ClientDto> dtoClients = new ArrayList<>();

        List<Client> clients = clientRepository.findClientByCountryIsNotAndCards_StateIsFalse(countryCode);

        clients.forEach(client -> {
            dtoClients.add(this.createClientDto(client));
        });

        return dtoClients;
    }

    private ClientDto createClientDto (Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setLastNames(client.getLastNames());
        clientDto.setDni(client.getDni());
        clientDto.setCountry(client.getCountry());

        return clientDto;
    }

    public List<Client> findClientByLastNames (String lastNames) {

        return clientRepository.findByLastName(lastNames);
    }

    public List<ClientDto> findClientByLastNamesNative (String lastNames) {
        List<Tuple> tupleList = clientRepository.findByLastNameNative(lastNames);
        List<ClientDto> clientDtos = new ArrayList<>();

        tupleList.forEach(tuple -> {
            ClientDto clientDto = new ClientDto();
            clientDto.setId((Integer) tuple.get("id"));
            clientDto.setName((String) tuple.get("name"));
            clientDto.setLastNames((String) tuple.get("last_names"));
            clientDto.setDni((String) tuple.get("dni"));
            clientDto.setCountry((String) tuple.get("country"));

            clientDtos.add(clientDto);
        });
//        return tupleList.stream().map(tuple -> {
//            ClientDto clientDto = new ClientDto();
//            clientDto.setId(tuple.get(0, Integer.class));
//            clientDto.setName(tuple.get(1, String.class));
//            clientDto.setLastNames(tuple.get(2, String.class));
//            clientDto.setDni(tuple.get(3, String.class));
//            clientDto.setCountry(tuple.get(4, String.class));
//
//            return clientDto;
//        }).collect(Collectors.toList());

        return clientDtos;
    }
}
