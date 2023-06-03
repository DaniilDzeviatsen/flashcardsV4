package com.example.flashcardsv4.controllers;

;
import com.example.flashcardsv4.service.CardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/createCard")
public class CreateCardController extends HttpServlet {
    private CardService cardService;

    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        cardService=(CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long chapterId=Long.parseLong(req.getParameter("chapterId"));
        String question=req.getParameter("question");
        String answer=req.getParameter("answer");
        cardService.addCard(chapterId, question, answer, false);
        resp.sendRedirect(req.getContextPath()+EditorController.PATH+"?chapterId"+chapterId);
    }
}
