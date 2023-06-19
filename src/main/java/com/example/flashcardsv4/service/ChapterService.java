package com.example.flashcardsv4.service;


import com.example.flashcardsv4.models.Chapter;

import java.util.List;

public interface ChapterService {
    void removeChapter(long chapterId);

    List<Chapter> getAllChapters();

    void createNewChapter(String name);

}
