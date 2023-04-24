package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    void deleteAllByClient_Id(int clientId);
}
