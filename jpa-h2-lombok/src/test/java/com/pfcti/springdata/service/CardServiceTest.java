package com.pfcti.springdata.service;

import com.pfcti.springdata.model.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    private CardService cardService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void updateCardStatus() {
        cardService.updateCardStatus(1, false);
        List <Card> cards = entityManager.createQuery("SELECT c FROM Card c where c.id = 1").getResultList();

        assertEquals(false, cards.get(0).getState());
    }
}