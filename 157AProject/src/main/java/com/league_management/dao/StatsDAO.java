package com.league_management.dao;

import com.league_management.model.Stats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatsDAO {
    private Connection connection;

    // Constructor to initialize the connection
    public StatsDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new stat entry
    public boolean addStat(Stats stats) throws SQLException {
        String query = "INSERT INTO Stats (PlayerID, TeamID, Points, Assists, Rebounds) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, stats.getPlayerID());
            stmt.setInt(2, stats.getTeamID());
            stmt.setInt(3, stats.getPoints());
            stmt.setInt(4, stats.getAssists());
            stmt.setInt(5, stats.getRebounds());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to get stats by PlayerID
    public Stats getStatsByPlayerID(int playerID) throws SQLException {
        String query = "SELECT * FROM Stats WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Stats(
                        rs.getInt("PlayerID"),
                        rs.getInt("TeamID"),
                        rs.getInt("Points"),
                        rs.getInt("Assists"),
                        rs.getInt("Rebounds")
                );
            }
            return null;
        }
    }

    // Method to update stats for a player
    public boolean updateStats(Stats stats) throws SQLException {
        String query = "UPDATE Stats SET Points = ?, Assists = ?, Rebounds = ? WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, stats.getPoints());
            stmt.setInt(2, stats.getAssists());
            stmt.setInt(3, stats.getRebounds());
            stmt.setInt(4, stats.getPlayerID());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to delete stats for a player
    public boolean deleteStats(int playerID) throws SQLException {
        String query = "DELETE FROM Stats WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerID);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to retrieve all stats
    public List<Stats> getAllStats() throws SQLException {
        List<Stats> statsList = new ArrayList<>();
        String query = "SELECT * FROM Stats";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                statsList.add(new Stats(
                        rs.getInt("PlayerID"),
                        rs.getInt("TeamID"),
                        rs.getInt("Points"),
                        rs.getInt("Assists"),
                        rs.getInt("Rebounds")
                ));
            }
        }
        return statsList;
    }
}
