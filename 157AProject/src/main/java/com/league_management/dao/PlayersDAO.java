package com.league_management.dao;

import com.league_management.model.Players;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayersDAO {
    private Connection connection;

    // Constructor: initializes the connection
    public PlayersDAO(Connection connection) {
        this.connection = connection;
    }

    public int addPlayer(Players player) throws SQLException {
        String query = "INSERT INTO Players (TeamID, Name, Position) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, player.getTeamID());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getPosition());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        player.setPlayerID(generatedKeys.getInt(1));  // Set the generated playerID
                        return player.getPlayerID();
                    } else {
                        throw new SQLException("Creating player failed, no ID obtained.");
                    }
                }
            }
            return 0;  // Return 0 if player insertion fails
        }
    }


    //Method to delete all players from a team
    public void deletePlayersByTeamID(int teamID) throws SQLException {
        String sql = "DELETE FROM players WHERE teamID = ? LIMIT 3";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teamID);
            statement.executeUpdate();
        }
    }

    // Method to get a player by ID
    public Players getPlayerByID(int playerID) throws SQLException {
        String query = "SELECT * FROM Players WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Players(
                        rs.getInt("PlayerID"),
                        rs.getInt("TeamID"),
                        rs.getString("Name"),
                        rs.getString("Position")
                );
            }
            return null;
        }
    }
  

    // Method to retrieve all players in the league
    public List<Players> getAllPlayers() throws SQLException {
        List<Players> players = new ArrayList<>();
        String query = "SELECT * FROM Players";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                players.add(new Players(
                        rs.getInt("PlayerID"),
                        rs.getInt("TeamID"),
                        rs.getString("Name"),
                        rs.getString("Position")
                ));
            }
        }
        return players;
    }
}
