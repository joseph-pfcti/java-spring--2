package com.pfcti.springwebservices.soap;

import io.spring.guides.gs_producing_web_service.GetClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import io.spring.guides.gs_producing_web_service.GetClientRequest;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientEndpointTest {
    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetClientRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetClientRequest request = new GetClientRequest();
        request.setId(1);
        GetClientResponse response = (GetClientResponse) ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);
        System.out.println("response : " + response.getClient());
        System.out.println("response : " + response.getClient().getName());
        assertThat(response != null);
        assertThat(response.getClient() != null);
    }


}