package com.example.flashcardsv4.repositories;



import com.example.flashcardsv4.exceptions.RepositoryException;
import com.example.flashcardsv4.models.Chapter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterRepositoryImpl implements ChapterRepository {
    private final DataSource db;

    public ChapterRepositoryImpl(DataSource db) {
        this.db = db;
    }


    @Override
    public void createNewChapter(String name) {
        String sql = """
                INSERT INTO chapter (name)
                VALUES (?);
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteChapter(long id) {
        String sql = """
                DELETE FROM chapter
                WHERE chapter.id = ? """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Chapter> getAllChapters() {
        String sql = """
                SELECT chapter.name AS name,
                                     chapter.id AS chapter_id,
                                     COUNT(card.id) AS total_questions,
                                     COUNT (card.id) FILTER ( WHERE is_remembered) AS learned_questions
                              FROM chapter
                                       LEFT JOIN card ON chapter.id = card.chapter_id 
                              GROUP BY chapter.id;
                 """;
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Chapter> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Chapter(
                        resultSet.getLong("chapter_id"),
                        resultSet.getString("name"),
                        resultSet.getLong("total_questions"),
                        resultSet.getLong("learned_questions")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public boolean ifChapterExists(long chapterId) {
        String sql= """
                SELECT TRUE
                FROM chapter
                WHERE chapter.id = ?;
                """;
        try(
                Connection connection=db.getConnection();
                PreparedStatement statement=connection.prepareStatement(sql);
                ){
            statement.setLong(1, chapterId);
            ResultSet resultSet=statement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            throw new RepositoryException(e);
        }
    }
}

