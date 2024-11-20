package com.league_management.dao;

import com.league_management.model.Stats;

import java.sql.Connection;

public class StatsDAOTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            StatsDAO statsDAO = new StatsDAO(connection);
           
            // Test retrieving stats by PlayerID
            Stats stats = statsDAO.getStatsByPlayerID(1);
            if (stats != null) {
                System.out.println("Retrieved Stats: " + stats);
            }

            // Test retrieving all stats
            System.out.println("All Stats: " + statsDAO.getAllStats());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
