package com.example.flashcardsv4.service;


import com.example.flashcardsv4.exceptions.InputDataException;
import com.example.flashcardsv4.models.Chapter;
import com.example.flashcardsv4.repositories.ChapterRepository;

import java.util.List;

public class ChapterServiceImpl implements ChapterService {
    ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public void removeChapter(long chapterId) {
        chapterRepository.deleteChapter(chapterId);
    }

    public void createNewChapter(String name) {
        if (!name.isEmpty()) {
            chapterRepository.createNewChapter(name);
        } else throw new InputDataException();
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.getAllChapters();
    }

}
