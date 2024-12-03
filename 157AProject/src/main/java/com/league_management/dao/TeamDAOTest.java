package com.league_management.dao;

import com.league_management.model.Team;

import java.sql.Connection;
import java.util.List;

public class TeamDAOTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TeamDAO teamDAO = new TeamDAO(connection);
           
            // Test retrieving team by TeamID
            Team teamTest1 = teamDAO.getTeamByID(1);
            if (teamTest1 != null) {
                System.out.println("Retrieved Stats for Team 1: " + teamTest1);
            }
            
            Team teamTest2 = teamDAO.getTeamByID(2);
            if (teamTest2 != null) {
                System.out.println("Retrieved Stats for Team 2: " + teamTest2);
            }
            
            Team teamTest3 = teamDAO.getTeamByID(10);
            if (teamTest3 != null) {
                System.out.println("Retrieved Stats for Team 10: " + teamTest3);
            }
            
            // Test for bad input from team DAO
            Team teamTest4 = teamDAO.getTeamByID(-1);
            if (teamTest4 != null) {
                System.out.println("Fetched invalid player ID, something went wrong.\n");
            } else {
            	System.out.println("Bad Player ID check passed\n");
            }

            List<Team> teamsList = teamDAO.getAllTeams();
            
            // Test retrieving all team
            for (Team team : teamsList) {
            	System.out.println("Team ID: " + team.getTeamID() + " Team Name: " + team.getTeamName()
            	+ " Wins: " + team.getWins() + " Losses: " + team.getLosses());
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
