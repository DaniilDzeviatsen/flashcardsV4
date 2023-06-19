package com.example.flashcardsv4.repositories;


import com.example.flashcardsv4.exceptions.RepositoryException;
import com.example.flashcardsv4.models.Card;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    private final DataSource db;

    public CardRepositoryImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public void deleteCard(long cardId) {
        String sql = """
                DELETE
                FROM card
                WHERE card.id = ?;
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setLong(1, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void addCard(long chapterId, String question, String answer) {
        String sql = """
                INSERT INTO card (chapter_id, question, answer, is_remembered)
                VALUES (?,?,?,?);
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setLong(1, chapterId);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, false);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateMemorizing(long cardId) {
        String sql = """
                UPDATE card
                SET is_remembered = TRUE
                WHERE card.id = ?;
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Card> showAllCards(long chapterId) {
        String sql = """
                SELECT card.id AS id,
                       card.question AS question,
                       card.answer AS answer,
                       card.is_remembered AS remembered,
                       card.chapter_id AS chapterId
                FROM card
                WHERE card.chapter_id = ?
                                 """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setLong(1, chapterId);
            ResultSet resultSet = statement.executeQuery();
            List<Card> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Card(
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("remembered"),
                        resultSet.getLong("id"),
                        resultSet.getLong("chapterId")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Card getCardById(long cardId) {
        String sql = """
                SELECT card.id AS id,
                       card.question AS question,
                       card.answer AS answer,
                       card.is_remembered AS remembered,
                       card.chapter_id AS chapterId
                FROM card
                WHERE card.id = ?
                                 """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setLong(1, cardId);

            ResultSet resultSet = statement.executeQuery();
            Card card = null;
            while (resultSet.next()) {
                card = new Card(

                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("remembered"),
                        resultSet.getLong("id"),
                        resultSet.getLong("chapterId")
                );
            }
            return card;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Card getOneCardData(long chapterId, long nextCardId) {
        String sql = """
                SELECT card.id AS id,
                       card.question AS question,
                       card.answer AS answer,
                       card.is_remembered AS remembered,
                       card.chapter_id AS chapterId
                FROM card
                WHERE card.chapter_id = ?
                  AND NOT card.is_remembered
                  AND card.id>?
                ORDER BY card.id
                LIMIT 1
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setLong(1, chapterId);
            statement.setLong(2, nextCardId);
            ResultSet resultSet = statement.executeQuery();
            Card card = null;
            while (resultSet.next()) {
                card = new Card(
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("remembered"),
                        resultSet.getLong("id"),
                        resultSet.getLong("chapterId")
                );
            }
            return card;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public boolean ifCardExists(long cardId) {
        String sql = """
                SELECT TRUE
                FROM card
                WHERE card.id = ?;
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, cardId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
