package com.pfcti.demo2.service;

import com.pfcti.demo2.dto.CardDto;
import com.pfcti.demo2.model.Card;
import com.pfcti.demo2.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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

        assertTrue(cards.get(0).getState() == false);
    }
}