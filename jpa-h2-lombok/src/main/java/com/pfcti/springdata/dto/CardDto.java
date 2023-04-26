package com.pfcti.springdata.dto;

import lombok.Data;

@Data
public class CardDto {
    private int id;
    private String cardNumber;
    private String type;
    private int clientId;
    private Boolean state;
}
