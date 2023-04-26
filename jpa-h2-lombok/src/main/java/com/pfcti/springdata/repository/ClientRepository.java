/*
 * Layer #2
 * Persistence layer Repository
 *
 * */

package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Client;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
    List<Client> findClientsByCountryAndAccounts_StateIsTrue(String countryCode);

    List<Client> findClientByCountryIsNotAndCards_StateIsFalse(String countryCode);

    List<Client> findClientByIdAndCards_StateIsTrueAndAccounts_StateIsTrue(int clientId);

    @Query(value = "SELECT c FROM Client c where c.lastNames = :lastNames")
    List<Client> findByLastName(String lastNames);

    @Query(value = "SELECT id, name, last_names, dni, country FROM client where last_names = :lastNames", nativeQuery = true)
    List<Tuple> findByLastNameNative(String lastNames);

    List<Client> findByDni(String dni);

    List<Client> findByNameContainingIgnoreCaseOrLastNamesContainingIgnoreCase(String name, String lastNames);

}
