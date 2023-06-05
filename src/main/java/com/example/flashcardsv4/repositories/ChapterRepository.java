package com.example.flashcardsv4.repositories;


import com.example.flashcardsv4.models.Chapter;

import java.util.List;

public interface ChapterRepository {

    List<Chapter> getAllChapters();

    void createNewChapter(String name);

    void deleteChapter(long chapterId);

    boolean ifChapterExists(long chapterId);
}
