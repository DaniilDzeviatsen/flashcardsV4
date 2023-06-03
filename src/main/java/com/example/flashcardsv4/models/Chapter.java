package com.example.flashcardsv4.models;

public class Chapter {
    private final long chapterId;
    private final String name;
    private long totalCardsNum;
    private long numOfLearnedCards;

    public Chapter(long chapterId, String name, long totalCardsNum, long numOfLearnedCards) {
        this.chapterId = chapterId;
        this.name = name;
        this.totalCardsNum=totalCardsNum;
        this.numOfLearnedCards=numOfLearnedCards;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId=" + chapterId +
                ", name='" + name + '\'' +
                ", totalCardsNum=" + totalCardsNum +
                ", numOfLearnedCards=" + numOfLearnedCards +
                '}';
    }

    public long getChapterId() {
        return chapterId;
    }

    public String getName() {
        return name;
    }

    public long getTotalCardsNum() {
        return totalCardsNum;
    }

    public long getNumOfLearnedCards() {
        return numOfLearnedCards;
    }
}
