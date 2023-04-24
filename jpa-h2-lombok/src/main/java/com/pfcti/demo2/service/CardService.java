package com.pfcti.demo2.service;

import com.pfcti.demo2.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;
}
