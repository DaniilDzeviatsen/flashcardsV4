package com.example.flashcardsv4.service;


import com.example.flashcardsv4.models.Card;

import java.util.List;

public interface CardService {

    long deleteCard(long cardId);

    void addCard(long chapterId, String question, String answer);

    void updateMemorizingToTrueForTraining(long cardId);

    Card getOneNextCardDataForTraining(long chapterId, long nextCardId);

    List<Card> showAllCards(long chapterId);

    public Card getNextCard(long cardId);


}
