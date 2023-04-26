package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    void deleteAllByClient_Id(int clientId);

    List<Account> findByClient_IdAndStateIsTrue(int clientId);
}
