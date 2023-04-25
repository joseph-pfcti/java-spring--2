package com.pfcti.demo2.repository;

import com.pfcti.demo2.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    void deleteAllByClient_Id(int clientId);

    @Modifying
    @Query(value = "update Card c set c.state=:state where c.id=:cardId")
    void updateStateById(Boolean state, int cardId);
}
