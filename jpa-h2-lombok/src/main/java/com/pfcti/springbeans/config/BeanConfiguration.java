package com.pfcti.springbeans.config;

import com.pfcti.springbeans.ClientAdministrator;
import com.pfcti.springbeans.dto.ClientQueryType;
import com.pfcti.springdata.repository.ClientRepository;
import com.pfcti.springdata.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanConfiguration {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @Bean({"defaultDni", "criteriaByDni"})
    public ClientAdministrator clientAdministratorByDni() {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.DNI);
    }

    @Bean("defaultName")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public ClientAdministrator clientAdministratorByName () {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.NAME);
    }

    @Bean("defaultNamePrototype")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Lazy
    public ClientAdministrator clientAdministratorByNamePrototype () {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.NAME);
    }

    @Bean("requestScope")
    @RequestScope
    public ClientAdministrator clientAdministratorByNameRequest () {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.NAME);
    }

    @Bean("sessionScope")
    @SessionScope
    public ClientAdministrator clientAdministratorByNameSession () {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.NAME);
    }

    @Bean("applicationScope")
    @ApplicationScope
    public ClientAdministrator clientAdministratorByNameApplication () {
        return new ClientAdministrator(clientRepository, clientService, ClientQueryType.NAME);
    }
}
