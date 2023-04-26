package com.pfcti.springbeans.business;

import com.pfcti.springbeans.business.service.ClientSearcherDB;
import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springbeans.dto.ClientQueryType;
import com.pfcti.springdata.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientSearcherTest {

    @Autowired
    private ClientSearcher dataBaseService;

    @Autowired
    private ClientSearcher externalService;

    /**
     * If you want to specify the name of service, you can add the @Qualifier
     * @Autowired
     * @Qualifier("externalService")
     * private ClientSearcher myService;
     */

    @Test
    void getDatabaseClients() {
        ClientQueryDto clientQueryDto = new ClientQueryDto();
        clientQueryDto.setTextFilter("1");
        clientQueryDto.setClientQueryType(ClientQueryType.DNI);

        List<ClientDto> clientDtoList = dataBaseService.getClients(clientQueryDto);
        System.out.println("DATABASE CLIENT LIST " + clientDtoList.size());

        assertEquals(1, clientDtoList.size());
    }

    @Test
    void getExternalClients() {
        ClientQueryDto clientQueryDto = new ClientQueryDto();
        clientQueryDto.setTextFilter("1-1111-1111");
        clientQueryDto.setClientQueryType(ClientQueryType.DNI);

        List<ClientDto> clientDtoList = externalService.getClients(clientQueryDto);
        System.out.println("EXTERNAL CLIENT LIST " + clientDtoList.size());
        assertEquals(1, clientDtoList.size());
    }

    /**
     * This is only in case if you want to use the service class instead of interface
     * Spring mapped into his container the interface and make it a relation with the declared services
     * But it doesn't indicate that you cannot use the service directly
     */

//    @Autowired
//    private ClientSearcherDB searcherDB;
//
//
//    @Test
//    void getDatabaseSearcher() {
//        ClientQueryDto clientQueryDto = new ClientQueryDto();
//        clientQueryDto.setTextFilter("1");
//        clientQueryDto.setClientQueryType(ClientQueryType.DNI);
//
//        List<ClientDto> clientDtoList = searcherDB.getClients(clientQueryDto);
//        System.out.println("DATABASE CLIENT LIST " + clientDtoList.size());
//
//        assertEquals(1, clientDtoList.size());
//    }
}