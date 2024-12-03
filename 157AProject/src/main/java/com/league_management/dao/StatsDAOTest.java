package com.league_management.dao;

import com.league_management.model.Stats;

import java.sql.Connection;
import java.util.List;

public class StatsDAOTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            StatsDAO statsDAO = new StatsDAO(connection);
           
         // Test retrieving team by TeamID
            Stats statTest1 = statsDAO.getStatsByPlayerID(1);
            if (statTest1 != null) {
                System.out.println("Retrieved Stats for Team 1: " + statTest1);
            }
            
            Stats statTest2 = statsDAO.getStatsByPlayerID(2);
            if (statTest2 != null) {
                System.out.println("Retrieved Stats for Team 2: " + statTest2);
            }
            
            Stats statTest3 = statsDAO.getStatsByPlayerID(18);
            if (statTest3 != null) {
                System.out.println("Retrieved Stats for Team 10: " + statTest3);
            }
            
            // Test for bad input from team DAO
            Stats statTest4 = statsDAO.getStatsByPlayerID(-1);
            if (statTest4 != null) {
                System.out.println("Fetched invalid player ID, something went wrong.\n");
            } else {
            	System.out.println("Bad Player ID check passed\n");
            }

            List<Stats> statList = statsDAO.getAllStats();
            
            // Test retrieving all statistics
            for (Stats stat : statList) {
            	System.out.println("Player ID: " + stat.getPlayerID() + " Player Name: " + stat.getPlayerName() 
            	+ " Points: " + stat.getPoints() + " Assists: " + stat.getAssists() + " Rebounds: " + stat.getRebounds());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
