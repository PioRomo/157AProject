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

    // Method to add a new player
    public boolean addPlayer(Players player) throws SQLException {
        String query = "INSERT INTO Players (PlayerID, TeamID, Name, Position) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, player.getPlayerID());
            stmt.setInt(2, player.getTeamID());
            stmt.setString(3, player.getName());
            stmt.setString(4, player.getPosition());
            return stmt.executeUpdate() > 0;
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

    // Method to update a player's information
    public boolean updatePlayer(Players player) throws SQLException {
        String query = "UPDATE Players SET TeamID = ?, Name = ?, Position = ? WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, player.getTeamID());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getPosition());
            stmt.setInt(4, player.getPlayerID());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to delete a player by ID
    public boolean deletePlayer(int playerID) throws SQLException {
        String query = "DELETE FROM Players WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerID);
            return stmt.executeUpdate() > 0;
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
