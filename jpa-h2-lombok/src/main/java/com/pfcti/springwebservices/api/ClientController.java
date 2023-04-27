package com.pfcti.springwebservices.api;

import com.pfcti.springdata.dto.ClientDto;
import com.pfcti.springdata.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/client")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDto findClient (@PathVariable int id) {
        log.info("Find the client id: {}", id);
        return clientService.fromClientToClientDto(clientService.find(id));
    }

    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ClientDto findClientXML(@PathVariable int id) {
        log.info("Find the client id: {}", id);
        return clientService.fromClientToClientDto(clientService.find(id));
    }

    @GetMapping(value = "/xml/json/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClientDto findClientXMLJson(@PathVariable int id) {
        log.info("Find the client id: {}", id);
        return clientService.fromClientToClientDto(clientService.find(id));
    }

    @GetMapping(value = "/parameter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClientDto findClientByQueryParam(@RequestParam int id) {
        log.info("Find the client id: {}", id);
        return clientService.fromClientToClientDto(clientService.find(id));
    }

    @GetMapping("/all")
    public List<ClientDto> getClients() {
        return clientService.findAllClients();
    }

    @PostMapping
    public void saveClient (@Valid @RequestBody ClientDto clientDto) {
        log.info("Parameters to save a new client {}", clientDto);
        clientService.insert(clientDto);
    }

    @PutMapping
    public void updateClient (@Valid @RequestBody ClientDto clientDto) {
        log.info("Parameters to update a new client {}", clientDto);
        clientService.update(clientDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient (@PathVariable int id) {
        log.info("Parameters to delete a client {}", id);
        clientService.delete(id);
    }
}
