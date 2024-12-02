package com.league_management.dao;

import com.league_management.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private Connection connection;

    // Constructor: initializes the connection
    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    public int addTeam(Team team) throws SQLException {
        String query = "INSERT INTO Teams (TeamName, Wins, Losses) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, team.getTeamName());
            stmt.setInt(2, team.getWins());
            stmt.setInt(3, team.getLosses());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding team failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated teamID
                } else {
                    throw new SQLException("Adding team failed, no ID obtained.");
                }
            }
        }
    }


    // Method to get a team by ID
    public Team getTeamByID(int teamID) throws SQLException {
        String query = "SELECT * FROM Teams WHERE TeamID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, teamID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Team(
                        rs.getInt("TeamID"),
                        rs.getString("TeamName"),
                        rs.getInt("Wins"),
                        rs.getInt("Losses")
                );
            }
            return null;
        }
    }

    // Method to update a team's record
    public boolean updateTeam(Team team) throws SQLException {
        String query = "UPDATE Teams SET TeamName = ?, Wins = ?, Losses = ? WHERE TeamID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, team.getTeamName());
            stmt.setInt(2, team.getWins());
            stmt.setInt(3, team.getLosses());
            stmt.setInt(4, team.getTeamID());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to delete a team
    public boolean deleteTeam(int teamID) throws SQLException {
        String query = "DELETE FROM Teams WHERE TeamID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, teamID);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to retrieve all teams
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM Teams";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teams.add(new Team(
                        rs.getInt("TeamID"),
                        rs.getString("TeamName"),
                        rs.getInt("Wins"),
                        rs.getInt("Losses")
                ));
            }
        }
        return teams;
    }
}
