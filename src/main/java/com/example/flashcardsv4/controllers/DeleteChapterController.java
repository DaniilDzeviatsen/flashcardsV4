package com.example.flashcardsv4.controllers;


import com.example.flashcardsv4.service.ChapterService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/deleteChapter")
public class DeleteChapterController extends HttpServlet {
private ChapterService chapterService;

    @Override
    public void init() throws ServletException {
        ServletContext context=getServletContext();
        chapterService= (ChapterService) context.getAttribute("chapterService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long chapterId=Long.parseLong(req.getParameter("chapterId"));
        chapterService.removeChapter(chapterId);
        resp.sendRedirect(req.getContextPath()+EditorController.PATH);
    }
}
