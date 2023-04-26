package com.pfcti.springbeans.business.service;

import com.pfcti.springbeans.business.ClientSearcher;
import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springbeans.dto.ClientQueryType;
import com.pfcti.springdata.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("externalService")
public class ClientSearcherExternal implements ClientSearcher {

    @Override
    public List<ClientDto> getClients(ClientQueryDto clientQueryDto) {
        List<ClientDto> clientDtoList = List.of(
                new ClientDto(1, "Joseph", "Ramirez Marchena", "1-1111-1111", "8888-8888", "CR", null, null, null),
                new ClientDto(2, "John", "Snow", "2-2222-2222", "9999-9999", "CR", null, null, null)
        );
        return clientDtoList.stream().filter(filter ->
                clientQueryDto.getClientQueryType() == ClientQueryType.NAME
                        ? filter.getName().equals(clientQueryDto.getTextFilter())
                        : filter.getDni().equals(clientQueryDto.getTextFilter())
        ).toList();
    }
}
