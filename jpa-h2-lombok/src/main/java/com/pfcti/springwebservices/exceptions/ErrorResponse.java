package com.pfcti.springwebservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int code;
    private int status;

    public ErrorResponse (String message, int status) {
        this.message = message;
        this.status = status;
    }
}
