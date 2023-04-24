package com.pfcti.demo2.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientDto {
    private int id;
    private String name;
    private String lastNames;
    private String dni;
    private String phone;
    private String country;

    private List<AddressDto> addresses;
    private List<AccountDto> accounts;
}
