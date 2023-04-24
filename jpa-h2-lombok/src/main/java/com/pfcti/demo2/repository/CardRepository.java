package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    void deleteAllByClient_Id(int clientId);
}
