package com.example.flashcardsv4.controllers;

import com.example.flashcardsv4.service.ChapterService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/create-chapter")
public class CreateChapterController extends HttpServlet {
    private ChapterService chapterService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        chapterService = (ChapterService) context.getAttribute("chapterService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        chapterService.createNewChapter(name);
        resp.sendRedirect(req.getContextPath()+MainPageController.PATH);
    }
}
