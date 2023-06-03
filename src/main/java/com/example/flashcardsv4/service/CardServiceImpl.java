package com.example.flashcardsv4.service;


import com.example.flashcardsv4.exceptions.InputDataException;
import com.example.flashcardsv4.models.Card;
import com.example.flashcardsv4.repositories.CardRepository;

import java.util.List;

public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void deleteCard(long cardId) {
        cardRepository.deleteCard(cardId);
    }

    public void addCard(long chapterId, String question, String answer, boolean isRemembered) {
        if (!question.isEmpty() && !answer.isEmpty()) {
            cardRepository.addCard(chapterId, question, answer, isRemembered);
        } else throw new InputDataException();
    }

    public void updateMemorizing(long cardId, long chapterId) {
        cardRepository.updateMemorizing(cardId);
        cardRepository.getOneCardData(chapterId, cardId);
    }

    public List<Card> getOneCardData(long chapterId, long offset) {
        return cardRepository.getOneCardData(chapterId, offset);
    }
    public List<Card> showAllCards(long chapterId){
        return cardRepository.showAllCards(chapterId);
    }
}
