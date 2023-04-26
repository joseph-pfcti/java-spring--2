package com.pfcti.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    private String name;
    private String lastNames;
    private String dni;
    private String phone;
    private String country;

    private List<AddressDto> addresses;
    private List<AccountDto> accounts;
    private List<CardDto> cards;
}
