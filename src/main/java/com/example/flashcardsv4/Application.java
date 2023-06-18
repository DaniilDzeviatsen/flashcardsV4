package com.example.flashcardsv4;


import com.example.flashcardsv4.repositories.CardRepository;
import com.example.flashcardsv4.repositories.CardRepositoryImpl;
import com.example.flashcardsv4.repositories.ChapterRepository;
import com.example.flashcardsv4.repositories.ChapterRepositoryImpl;
import com.example.flashcardsv4.service.CardService;
import com.example.flashcardsv4.service.CardServiceImpl;
import com.example.flashcardsv4.service.ChapterService;
import com.example.flashcardsv4.service.ChapterServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class Application implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public void contextInitialized(ServletContextEvent event) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("FLASHCARDS_DB_URL"));
        hikariConfig.setUsername(System.getenv("FLASHCARDS_DB_USER"));
        hikariConfig.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource db = new HikariDataSource(hikariConfig);

        ChapterRepository chapterRepository = new ChapterRepositoryImpl(db);
        CardRepository cardRepository = new CardRepositoryImpl(db);
        CardService cardService = new CardServiceImpl(cardRepository, chapterRepository);
        ChapterService chapterService = new ChapterServiceImpl(chapterRepository);
        ServletContext context = event.getServletContext();
        context.setAttribute("dataSource", db);
        context.setAttribute("cardService", cardService);
        context.setAttribute("chapterService", chapterService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        HikariDataSource db = (HikariDataSource) context.getAttribute("dataSource");
        db.close();
    }
}
