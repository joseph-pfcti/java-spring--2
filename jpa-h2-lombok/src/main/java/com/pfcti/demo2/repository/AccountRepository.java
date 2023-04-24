package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    void deleteAllByClient_Id(int clientId);
}
