package com.pfcti.springdata.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private List<AddressDto> addresses;
    private List<AccountDto> accounts;
    private List<CardDto> cards;
}
