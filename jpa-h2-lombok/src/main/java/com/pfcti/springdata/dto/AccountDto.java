package com.pfcti.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private int id;
    private String accountNumber;
    private String type;
    private Boolean state;
    private int clientId;
}
