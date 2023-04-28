package com.pfcti.springwebservices.api;

import com.pfcti.springdata.dto.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerTest {

    @LocalServerPort
    private Integer port = 0;
    private String BASEURL = "http://localhost";
    private static final String BASE_REST_URL = "/v1/api/client";
    private WebTestClient webTestClient = null;


    @BeforeEach
    void setUp() {
        BASEURL = BASEURL.concat(":").concat(port.toString()).concat(BASE_REST_URL);
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl(BASEURL)
                .responseTimeout(Duration.ofSeconds(3600))
                .build();
    }

    @Test
    void getClients() {
        List<ClientDto> clientDtos = this.webTestClient
                .get()
                .uri("/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        System.out.println("clientDtos : " + clientDtos);
        assertFalse(clientDtos.isEmpty());
        assertTrue(clientDtos.get(0).getName() != null);
    }
}