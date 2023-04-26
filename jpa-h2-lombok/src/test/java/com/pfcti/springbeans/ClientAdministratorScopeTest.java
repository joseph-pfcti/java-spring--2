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
class ClientAdministratorScopeTest {
    @Autowired
    @Qualifier("defaultName")
    private ClientAdministrator singletonName1;

    @Autowired
    @Qualifier("defaultName")
    private ClientAdministrator singletonName2;

    @Autowired
    @Qualifier("defaultNamePrototype")
    private ClientAdministrator prototypeName1;

    @Autowired
    @Qualifier("defaultNamePrototype")
    private ClientAdministrator prototypeName2;

    @Autowired
    private ClientAdministrator requestScope;

    @Autowired
    private ClientAdministrator sessionScope;

    @Autowired
    private ClientAdministrator applicationScope;

    @Test
    void instances () {
        System.out.println("SINGLETON 1: " + singletonName1);
        System.out.println("SINGLETON 2: " + singletonName2);
        System.out.println("PROTOTYPE 1: " + prototypeName1);
        System.out.println("PROTOTYPE 2: " + prototypeName2);

        assertEquals(1, 1);
    }
}