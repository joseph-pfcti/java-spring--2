package com.pfcti.demo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    private int id;
    private String name;
    @Column(length = 30)
    private String lastNames;
    @Column(columnDefinition = "varchar(9)")
    private String dni;
    private String phone;
}
