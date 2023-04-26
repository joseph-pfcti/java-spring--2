package com.pfcti.springdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Account number cannot be null")
    @NotBlank(message = "Account number cannot be empty")
    @Size(min = 2, message = "Account number needs to have at least 2 characters")
    private String accountNumber;

    @NotNull(message = "Type cannot be null")
    @Size(min = 1, message = "Type needs to have at least 1 characters")
    private String type;

    @AssertTrue(message = "State must be true")
    private Boolean state;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
