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

 // Method to add a new game and update teams win/loss record
    public boolean addGame(Games game) throws SQLException {
        String insertGameQuery = "INSERT INTO Games (HomeTeamID, AwayTeamID, HomeTeamScore, AwayTeamScore) VALUES (?, ?, ?, ?)";
        String updateWinsQuery = "UPDATE Teams SET Wins = Wins + 1 WHERE TeamID = ?";
        String updateLossesQuery = "UPDATE Teams SET Losses = Losses + 1 WHERE TeamID = ?";
        
        try (
            PreparedStatement insertGameStmt = connection.prepareStatement(insertGameQuery);
            PreparedStatement updateWinsStmt = connection.prepareStatement(updateWinsQuery);
            PreparedStatement updateLossesStmt = connection.prepareStatement(updateLossesQuery)
        ) {
            // Add the game record
            insertGameStmt.setInt(1, game.getHomeTeamID());
            insertGameStmt.setInt(2, game.getAwayTeamID());
            insertGameStmt.setInt(3, game.getHomeTeamScore());
            insertGameStmt.setInt(4, game.getAwayTeamScore());
            int rowsAffected = insertGameStmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Determine the winner and loser
                if (game.getHomeTeamScore() > game.getAwayTeamScore()) {
                    // Home team wins
                    updateWinsStmt.setInt(1, game.getHomeTeamID());
                    updateLossesStmt.setInt(1, game.getAwayTeamID());
                } else {
                    // Away team wins
                    updateWinsStmt.setInt(1, game.getAwayTeamID());
                    updateLossesStmt.setInt(1, game.getHomeTeamID());
                }
                
                // Execute the updates
                updateWinsStmt.executeUpdate();
                updateLossesStmt.executeUpdate();
            }
            
            return rowsAffected > 0;
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
