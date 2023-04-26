package com.pfcti.springbeans.business.service;

import com.pfcti.springbeans.business.ClientSearcher;
import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springbeans.dto.ClientQueryType;
import com.pfcti.springdata.dto.ClientDto;
import com.pfcti.springdata.model.Client;
import com.pfcti.springdata.repository.ClientRepository;
import com.pfcti.springdata.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("dataBaseService")
public class ClientSearcherDB implements ClientSearcher {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public List<ClientDto> getClients(ClientQueryDto clientQueryDto) {
        List<Client> clients = null;
        if (ClientQueryType.DNI.equals(clientQueryDto.getClientQueryType())) {
            clients = this.clientRepository.findByDni(clientQueryDto.getTextFilter());
        } else if (ClientQueryType.NAME.equals(clientQueryDto.getClientQueryType())) {
            clients = this.clientRepository.findByNameContainingIgnoreCaseOrLastNamesContainingIgnoreCase(clientQueryDto.getTextFilter(), clientQueryDto.getTextFilter());
        }

        return Optional
                .ofNullable(clients)
                .map(clientsAux-> clientsAux.stream()
                        .map(client-> this.clientService.fromClientToClientDto(client))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
