package com.example.flashcardsv4.repositories;


import com.example.flashcardsv4.models.Card;

import java.util.List;

public interface CardRepository {
    void deleteCard(long cardId);

    void addCard(long chapterId, String question, String answer);

    void updateMemorizing(long cardId);

    List<Card> showAllCards(long chapterId);

    List<Card> getOneCardData(long chapterId, long offset);
    boolean ifCardExists(long cardId);
}
