package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Client;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findClientsByCountryAndAccounts_StateIsTrue(String countryCode);

    List<Client> findClientByCountryIsNotAndCards_StateIsFalse(String countryCode);

    @Query(value = "SELECT c FROM Client c where c.lastNames = :lastNames")
    List<Client> findByLastName(String lastNames);

    @Query(value = "SELECT id, name, last_names, dni, country FROM client where last_names = :lastNames", nativeQuery = true)
    List<Tuple> findByLastNameNative(String lastNames);

}
