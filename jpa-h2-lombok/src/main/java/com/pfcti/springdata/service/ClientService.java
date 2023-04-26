/*
 * Layer #3
 * Persistence layer Service
 *
 * */

package com.pfcti.springdata.service;

import com.pfcti.springdata.criteria.ClientSpecification;
import com.pfcti.springdata.dto.ClientDto;
import com.pfcti.springdata.dto.ProductDto;
import com.pfcti.springdata.model.Account;
import com.pfcti.springdata.model.Card;
import com.pfcti.springdata.model.Client;
import com.pfcti.springdata.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
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

    private ClientSpecification clientSpecification;

    private CardService cardService;
    private AccountService accountService;

    public void insert(ClientDto clientDto) {
        Client client = fromClientDtoToClientEntity(clientDto);
        clientRepository.save(client);
    }

    public Client find(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> {
            throw new RuntimeException("Client doesn't exist");
        });

        return client;
    }

    public void update(ClientDto clientDto) {
        Client client = fromClientDtoToClientEntity(clientDto);
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
            dtoClients.add(this.fromClientToClientDto(client));
        });

        return dtoClients;
    }

    public List<ClientDto> findClientByCountryAndCardsStateIsFalse (String countryCode) {

        List<ClientDto> dtoClients = new ArrayList<>();

        List<Client> clients = clientRepository.findClientByCountryIsNotAndCards_StateIsFalse(countryCode);

        clients.forEach(client -> {
            dtoClients.add(this.fromClientToClientDto(client));
        });

        return dtoClients;
    }

    public ClientDto fromClientToClientDto (Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);

//        if (!client.getCards().isEmpty()) {
//            clientDto.setCards(cardService.convertCardListToCardListDto(client.getCards()));
//        }
//
//        if (!client.getAccounts().isEmpty()) {
//            clientDto.setAccounts(accountService.convertAccountListToAccountListDto(client.getAccounts()));
//        }

        return clientDto;
    }

    private Client fromClientDtoToClientEntity (ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
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

    public List<ClientDto> findByCriteria(ClientDto clientDtoFilter) {
        return clientRepository
                .findAll(clientSpecification.buildFilter(clientDtoFilter))
                .stream()
                .map(this::fromClientToClientDto)
                .collect(Collectors.toList());
    }

    public List<ClientDto> findByClientIdAndGetActiveProducts (int clientId) {
        List<ClientDto> clientDtoList = new ArrayList<>();
        List<Client> clients = clientRepository.findClientByIdAndCards_StateIsTrueAndAccounts_StateIsTrue(clientId);

        clients.forEach(client -> {
            clientDtoList.add(this.fromClientToClientDto(client));
        });

        return clientDtoList;
    }

    public ProductDto findClientProductsByClientId (int clientId) {
        ProductDto productDto = new ProductDto();

        List<Card> cards = cardRepository.findByClient_IdAndStateIsTrue(clientId);
        productDto.setCards(cardService.convertCardListToCardListDto(cards));

        List<Account> accounts = accountRepository.findByClient_IdAndStateIsTrue(clientId);
        productDto.setAccounts(accountService.convertAccountListToAccountListDto(accounts));

        return productDto;
    }
}
