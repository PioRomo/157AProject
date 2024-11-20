package com.league_management.controller;

import com.league_management.dao.TeamDAO;
import com.league_management.model.Team;
import com.league_management.dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {

    private TeamDAO teamDAO;

    @Override
    public void init() {
        // Initialize TeamDAO with a database connection
		try {
			Connection connection = DatabaseConnection.getConnection();
			teamDAO = new TeamDAO(connection);
			
		} catch (Exception e){
			System.err.println("Error initializing servlet: " + e.getMessage());
	        e.printStackTrace();
		}
    }

    // Handle GET request to display all teams
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve all teams from the database
            List<Team> teams = teamDAO.getAllTeams();

            // Set the teams as a request attribute and forward to JSP page
            request.setAttribute("teams", teams);
            request.getRequestDispatcher("/teams.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving teams", e);
        }
    }

    // Handle POST request to add a new team
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String teamName = request.getParameter("teamName");
        int wins = Integer.parseInt(request.getParameter("wins"));
        int losses = Integer.parseInt(request.getParameter("losses"));

        // Create a new Team object
        Team newTeam = new Team();
        newTeam.setTeamName(teamName);
        newTeam.setWins(wins);
        newTeam.setLosses(losses);

        try {
            // Add the team to the database
            boolean added = teamDAO.addTeam(newTeam);

            // Redirect to the list of teams after adding
            if (added) {
                response.sendRedirect("team");
            } else {
                request.setAttribute("error", "Failed to add team");
                request.getRequestDispatcher("/addTeam.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error adding team", e);
        }
    }

    // Handle PUT request to update an existing team (if you implement this method)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters for updating the team
        int teamID = Integer.parseInt(request.getParameter("teamID"));
        String teamName = request.getParameter("teamName");
        int wins = Integer.parseInt(request.getParameter("wins"));
        int losses = Integer.parseInt(request.getParameter("losses"));

        // Create a new Team object
        Team updatedTeam = new Team();
        updatedTeam.setTeamID(teamID);
        updatedTeam.setTeamName(teamName);
        updatedTeam.setWins(wins);
        updatedTeam.setLosses(losses);

        try {
            // Update the team in the database
            boolean updated = teamDAO.updateTeam(updatedTeam);

            // Redirect to the team list if updated successfully
            if (updated) {
                response.sendRedirect("team");
            } else {
                request.setAttribute("error", "Failed to update team");
                request.getRequestDispatcher("/editTeam.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error updating team", e);
        }
    }

    // Handle DELETE request to delete a team
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamID = Integer.parseInt(request.getParameter("teamID"));

        try {
            // Delete the team from the database
            boolean deleted = teamDAO.deleteTeam(teamID);

            // Redirect to the team list after deletion
            if (deleted) {
                response.sendRedirect("team");
            } else {
                request.setAttribute("error", "Failed to delete team");
                request.getRequestDispatcher("/teamList.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error deleting team", e);
        }
    }
}
