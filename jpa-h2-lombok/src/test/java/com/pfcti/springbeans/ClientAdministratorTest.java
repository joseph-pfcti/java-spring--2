package com.pfcti.springbeans;

import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springbeans.dto.ClientQueryType;
import com.pfcti.springdata.dto.ClientDto;
import com.pfcti.springdata.repository.ClientRepository;
import com.pfcti.springdata.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClientAdministratorTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @Test
    void getClientsByCriteria() {
        ClientAdministrator clientAdministrator = new ClientAdministrator(clientRepository, clientService);
        ClientQueryDto clientQueryDto = new ClientQueryDto();

        clientQueryDto.setTextFilter("1");
        clientQueryDto.setClientQueryType(ClientQueryType.DNI);

        List<ClientDto> clientDtos = clientAdministrator.getClientsByCriteria(clientQueryDto);
        assertEquals(1, clientDtos.size());
    }
}