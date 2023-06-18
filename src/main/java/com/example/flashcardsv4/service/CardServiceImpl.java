package com.example.flashcardsv4.service;


import com.example.flashcardsv4.exceptions.InputDataException;
import com.example.flashcardsv4.models.Card;
import com.example.flashcardsv4.repositories.CardRepository;
import com.example.flashcardsv4.repositories.ChapterRepository;

import java.util.List;

public class CardServiceImpl implements CardService {
    CardRepository cardRepository;
    ChapterRepository chapterRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public long deleteCard(long cardId) {
        Card card;
        if (cardRepository.ifCardExists(cardId)) {
            card = cardRepository.getCardById(cardId);
            cardRepository.deleteCard(cardId);
        } else throw new InputDataException();
        return card.getChapterId();
    }

    public void addCard(long chapterId, String question, String answer) {
        if (chapterRepository.ifChapterExists(chapterId)) {
            if (!question.isEmpty() && !answer.isEmpty()) {
                cardRepository.addCard(chapterId, question, answer);
            } else throw new InputDataException();
        } else throw new InputDataException();
    }

    @Override
    public void updateMemorizingToTrueForTraining(long cardId) {
        if (cardRepository.ifCardExists(cardId)) {
            cardRepository.updateMemorizing(cardId);
        } else throw new InputDataException();
    }

    public Card getOneNextCardDataForTraining(long chapterId, long nextCardId) {
        if (chapterRepository.ifChapterExists(chapterId)) {
            return cardRepository.getOneCardData(chapterId, nextCardId);
        } else throw new InputDataException();
    }

    public List<Card> showAllCards(long chapterId) {
        return cardRepository.showAllCards(chapterId);
    }

    @Override
    public Card getNextCard(long cardId) {
        Card card;
        if (cardRepository.ifCardExists(cardId)) {
            card = cardRepository.getCardById(cardId);
        } else throw new InputDataException();
        return cardRepository.getOneCardData(card.getChapterId(), cardId);
    }
}
