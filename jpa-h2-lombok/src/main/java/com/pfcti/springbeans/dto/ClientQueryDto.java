package com.pfcti.springbeans.dto;

import lombok.Data;

@Data
public class ClientQueryDto {
    private String textFilter;
    private ClientQueryType clientQueryType;
}
