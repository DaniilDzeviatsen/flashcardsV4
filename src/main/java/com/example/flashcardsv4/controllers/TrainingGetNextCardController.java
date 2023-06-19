package com.example.flashcardsv4.controllers;

import com.example.flashcardsv4.models.Card;
import com.example.flashcardsv4.service.CardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.flashcardsv4.controllers.TrainingGetNextCardController.PATH;

@WebServlet(urlPatterns = PATH)

public class TrainingGetNextCardController extends HttpServlet {
    CardService cardService;

    public static final String PATH="/training/next";

    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        cardService=(CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cardId=Long.parseLong(req.getParameter("cardId"));
        Card card=cardService.getNextCard(cardId);
        String responseBody=card.toString();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(responseBody);
    }
}
