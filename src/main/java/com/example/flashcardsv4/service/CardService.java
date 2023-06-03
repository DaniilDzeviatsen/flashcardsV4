package com.example.flashcardsv4.service;


import com.example.flashcardsv4.models.Card;

import java.util.List;

public interface CardService {

    void deleteCard(long cardId);

    void addCard(long chapterId, String question, String answer, boolean isRemembered);

    void updateMemorizing(long cardId, long chapterId);

    List<Card> getOneCardData(long chapterId, long offset);

    List<Card> showAllCards(long chapterId);

}
