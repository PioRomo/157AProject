package com.league_management.dao;
import com.league_management.model.Games;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GamesDAO {
    private Connection connection;

    // Constructor: initializes the connection
    public GamesDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new game
    public boolean addGame(Games game) throws SQLException {
        String query = "INSERT INTO Games (GameID, HomeTeamID, AwayTeamID, HomeTeamScore, AwayTeamScore) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, game.getGameID());
            stmt.setInt(2, game.getHomeTeamID());
            stmt.setInt(3, game.getAwayTeamID());
            stmt.setInt(4, game.getHomeTeamScore());
            stmt.setInt(5, game.getAwayTeamScore());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to get a game by ID
    public Games getGameByID(int gameID) throws SQLException {
        String query = "SELECT * FROM Games WHERE GameID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Games(
                        rs.getInt("GameID"),
                        rs.getInt("HomeTeamID"),
                        rs.getInt("AwayTeamID"),
                        rs.getInt("HomeTeamScore"),
                        rs.getInt("AwayTeamScore")
                );
            }
            return null;
        }
    }

    // Method to update a game's score
    public boolean updateGameScore(Games game) throws SQLException {
        String query = "UPDATE Games SET HomeTeamScore = ?, AwayTeamScore = ? WHERE GameID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, game.getHomeTeamScore());
            stmt.setInt(2, game.getAwayTeamScore());
            stmt.setInt(3, game.getGameID());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to delete a game by ID
    public boolean deleteGame(int gameID) throws SQLException {
        String query = "DELETE FROM Games WHERE GameID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, gameID);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to retrieve all games
    public List<Games> getAllGames() throws SQLException {
        List<Games> games = new ArrayList<>();
        String query = "SELECT * FROM Games";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                games.add(new Games(
                        rs.getInt("GameID"),
                        rs.getInt("HomeTeamID"),
                        rs.getInt("AwayTeamID"),
                        rs.getInt("HomeTeamScore"),
                        rs.getInt("AwayTeamScore")
                ));
            }
        }
        return games;
    }
}
