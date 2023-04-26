package com.pfcti.springbeans;

import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springdata.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientAdministratorAutowiredTest {
    @Autowired
    private ClientAdministrator criteriaByDni;

    @Autowired
    @Qualifier("defaultName")
    private ClientAdministrator defaultCriteriaByNames;


    @Test
    void getClientsByDni() {
        ClientQueryDto clientQueryDto = new ClientQueryDto();

        clientQueryDto.setTextFilter("1");

        List<ClientDto> clientDtos = criteriaByDni.getClientsByCriteria(clientQueryDto);
        assertEquals(1, clientDtos.size());
    }

    @Test
    void getClientsByName() {
        ClientQueryDto clientQueryDto = new ClientQueryDto();

        clientQueryDto.setTextFilter("ROBERTO");

        List<ClientDto> clientDtos = defaultCriteriaByNames.getClientsByCriteria(clientQueryDto);
        assertEquals(1, clientDtos.size());
    }
}