package com.pfcti.springdata.service;

import com.pfcti.springdata.dto.CardDto;
import com.pfcti.springdata.model.Card;
import com.pfcti.springdata.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;


    private Card fromCardDtoToCardEntity (CardDto cardDto) {
        Card card = new Card();
        BeanUtils.copyProperties(cardDto, card);
        return card;
    }

    private CardDto fromCardEntityToCardDto (Card card) {
        CardDto cardDto = new CardDto();
        BeanUtils.copyProperties(card, cardDto);
        return cardDto;
    }

    public List<CardDto> convertCardListToCardListDto (List<Card> cardList) {
        return cardList
                .stream()
                .map(this::fromCardEntityToCardDto)
                .collect(Collectors.toList());
    }

    public void updateCardStatus (int cardId, Boolean cardStatus) {
        cardRepository.updateStateById(cardStatus, cardId);
    }
}
