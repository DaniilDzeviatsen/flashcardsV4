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


@WebServlet(urlPatterns = "/training")
public class TrainingController extends HttpServlet {

    private CardService cardService;
    private ChapterService chapterService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
        chapterService = (ChapterService) context.getAttribute("chapterService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long chapterId = Long.parseLong(request.getParameter("chapterId"));
        long offset = Long.parseLong(request.getParameter("offset"));
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        response.getWriter().println(this.cardService.getOneCardData(chapterId, offset));
    }
}
