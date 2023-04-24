package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    void deleteAllByClient_Id(int clientId);
}
