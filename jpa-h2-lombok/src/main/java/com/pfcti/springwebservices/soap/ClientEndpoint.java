package com.pfcti.springwebservices.soap;

import com.pfcti.springdata.service.ClientService;
import io.spring.guides.gs_producing_web_service.GetClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import io.spring.guides.gs_producing_web_service.GetClientResponse;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {
    @Autowired
    private ClientService clientService;
    /**
     * "static final" is equals to const definition of a variable
     */
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
    @ResponsePayload
    public GetClientResponse getClient(@RequestPayload GetClientRequest request) {
        System.out.println(request + "REQUEST");
        GetClientResponse clientResponse = new GetClientResponse();
        clientResponse.setClient(clientService.getClientSoap(request.getId()));
        return clientResponse;
    }
}
