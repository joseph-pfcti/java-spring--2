package com.pfcti.springdata.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    @NotNull
    @Size(min = 3, max = 10)
    private String name;
    @NotNull
    private String lastNames;
    @NotNull
    @Size(min = 9)
    private String dni;
    private String phone;
    private String country;

    private List<AddressDto> addresses;
    private List<AccountDto> accounts;
    private List<CardDto> cards;
}
