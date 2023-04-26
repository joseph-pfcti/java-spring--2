package com.pfcti.springbeans.dto;

import lombok.Data;

@Data
public class AccountQueryDto {
    private String accountNumber;
    private Boolean state;
    private AccountQueryType accountQueryType;
}
