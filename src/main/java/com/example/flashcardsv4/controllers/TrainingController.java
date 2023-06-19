package com.example.flashcardsv4.controllers;


import com.example.flashcardsv4.models.Card;
import com.example.flashcardsv4.service.CardService;
import com.example.flashcardsv4.service.ChapterService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.flashcardsv4.controllers.TrainingController.PATH;


@WebServlet(urlPatterns = TrainingController.PATH)
public class TrainingController extends HttpServlet {

    private CardService cardService;
    private ChapterService chapterService;

    public static final String PATH="/training";

    @Override
    public void init() throws ServletException{
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
        chapterService = (ChapterService) context.getAttribute("chapterService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long chapterId = Long.parseLong(request.getParameter("chapterId"));
        long nextCardId = Long.parseLong(request.getParameter("nextCardId"));
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        response.getWriter().println(cardService.getOneNextCardDataForTraining(chapterId, nextCardId));
    }
}
