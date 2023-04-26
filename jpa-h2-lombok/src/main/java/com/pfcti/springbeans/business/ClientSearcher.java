package com.pfcti.springbeans.business;

import com.pfcti.springbeans.dto.ClientQueryDto;
import com.pfcti.springdata.dto.ClientDto;

import java.util.List;

public interface ClientSearcher {
    List<ClientDto> getClients(ClientQueryDto clientQueryDto);
}
