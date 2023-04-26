package com.pfcti.springdata.dto;

import lombok.Data;

@Data
public class InvestmentDto {
    private int id;
    private String number;
    private String type;
    private Boolean state;
    private int clientId;
}
