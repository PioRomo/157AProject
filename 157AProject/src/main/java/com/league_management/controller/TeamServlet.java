package com.league_management.controller;

import com.league_management.dao.TeamDAO;
import com.league_management.dao.PlayersDAO;

import com.league_management.model.Team;
import com.league_management.dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/teams")
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
        String method = request.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response); // Call the doDelete method to handle deletion
        } else {
            // Handle other POST logic (e.g., adding a team)
            String teamName = request.getParameter("teamName");
            int wins = Integer.parseInt(request.getParameter("wins"));
            int losses = Integer.parseInt(request.getParameter("losses"));

            Team newTeam = new Team();
            newTeam.setTeamName(teamName);
            newTeam.setWins(wins);
            newTeam.setLosses(losses);

            try {
                // Add team and retrieve teamID
                int teamID = teamDAO.addTeam(newTeam);

                if (teamID > 0) {
                    response.sendRedirect("teams");
                } else {
                    request.setAttribute("error", "Failed to add team");
                    request.getRequestDispatcher("/addTeam.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                throw new ServletException("Error adding team", e);
            }
        }
    }

   



    // Handle DELETE request to delete a team
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Parse the teamID from the request
            int teamID = Integer.parseInt(request.getParameter("teamID"));
            
            // Step 1: Delete players associated with the team
            PlayersDAO playersDAO = new PlayersDAO(DatabaseConnection.getConnection());
            playersDAO.deletePlayersByTeamID(teamID);

            // Step 2: Delete the team itself
            TeamDAO teamsDAO = new TeamDAO(DatabaseConnection.getConnection());
            teamsDAO.deleteTeam(teamID);

            // Respond with a success message
            response.sendRedirect("teams");
        } catch (Exception e) {
            // Handle errors
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting team: " + e.getMessage());
            e.printStackTrace();
        }
    }

}