package com.pfcti.springdata.repository;

import com.pfcti.springdata.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    void deleteAllByClient_Id(int clientId);

    List<Card> findByClient_IdAndStateIsTrue(int clientId);

    @Modifying
    @Query(value = "update Card c set c.state=:state where c.id=:cardId")
    void updateStateById(Boolean state, int cardId);
}
