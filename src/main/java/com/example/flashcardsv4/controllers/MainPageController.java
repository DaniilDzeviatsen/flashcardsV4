package com.example.flashcardsv4.controllers;

import com.example.flashcardsv4.models.Chapter;
import com.example.flashcardsv4.service.ChapterService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/mainPage")
public class MainPageController extends HttpServlet {
    private ChapterService chapterService;


    public void init() {
        ServletContext context = getServletContext();
        chapterService = (ChapterService)context.getAttribute("chapterService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List <Chapter> chapters=chapterService.getAllChapters();
        String responseBody=chapters.isEmpty()?"No one chapter exists":chapters.stream()
                .map(chapter->"%3s %s %s %s".formatted(
                        chapter.getChapterId(),
                        chapter.getName(),
                        chapter.getNumOfLearnedCards(),
                        chapter.getTotalCardsNum()
                ))
                .collect(Collectors.joining("\n"));
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseBody);
    }
}
