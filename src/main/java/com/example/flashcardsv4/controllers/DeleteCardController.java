package com.example.flashcardsv4.controllers;

import com.example.flashcardsv4.service.CardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/delete-card")
public class DeleteCardController extends HttpServlet {
    private CardService cardService;
    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        cardService=(CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cardId=Long.parseLong(req.getParameter("cardId"));
        long deletedCardChapterId=cardService.deleteCard(cardId);
        resp.sendRedirect(req.getContextPath()+EditorController.PATH+"?chapterId="+deletedCardChapterId);
    }
}
