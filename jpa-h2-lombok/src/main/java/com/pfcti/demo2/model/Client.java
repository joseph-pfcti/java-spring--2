/*
 * Layer #1
 * Persistence layer JPA
 *
 * */

package com.pfcti.demo2.model;

import com.pfcti.demo2.dto.AccountDto;
import com.pfcti.demo2.dto.AddressDto;
import com.pfcti.demo2.dto.CardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 30)
    private String lastNames;
    @Column(columnDefinition = "varchar(9)")
    private String dni;
    private String phone;
    private String country;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Account> accounts;
    @OneToMany(mappedBy = "client")
    private List<Address> addresses;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Card> cards;
    @OneToMany(mappedBy = "client")
    private List<Investment> investments;
}
