package com.pfcti.springdata.dto;

import lombok.Data;

@Data
public class AddressDto {
    private int id;
    private String address;
    private String code;
    private int clientId;
}
