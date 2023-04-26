package com.pfcti.springdata.dto;

import lombok.Data;

@Data
public class AccountDto {
    private int id;
    private String accountNumber;
    private String type;
    private Boolean state;
    private int clientId;
}
