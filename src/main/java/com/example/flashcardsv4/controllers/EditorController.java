package com.example.flashcardsv4.controllers;

import com.example.flashcardsv4.models.Card;
import com.example.flashcardsv4.service.CardService;
import com.example.flashcardsv4.service.ChapterService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet (urlPatterns = EditorController.PATH)
public class EditorController extends HttpServlet {

    private CardService cardService;

    public static final String PATH = "/editor";

    @Override
    public void init() throws ServletException{
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long chapterId=Long.valueOf(request.getParameter("chapterId"));
        List<Card> cards=cardService.showAllCards(chapterId);

        request.setAttribute("cards", cards);
        request.setAttribute("chapterId", chapterId);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/cards.jsp");
        dispatcher.forward(request,response);
        /*String responseBody=cards.isEmpty()?"No one card exists":cards.stream()
                .map(card->"%3s %s %s %s".formatted(
                        card.getId(),
                        card.getQuestion(),
                        card.getAnswer(),
                        card.isIs_remembered()
                ))
                .collect(Collectors.joining("\n"));
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseBody);*/
    }
}