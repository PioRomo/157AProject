<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.league_management.model.Players" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Players</title>
    <script src="https://cdn.tailwindcss.com"></script> <!-- Tailwind CSS -->
</head>
<body class="bg-gray-100 text-gray-800">
	<%@ include file="navbar.jsp" %>
    <div class="container mx-auto py-8">
        <h1 class="text-3xl font-bold text-center mb-8">Players</h1>

        <%
            // Retrieve the list of players from the request attribute
            List<Players> players = (List<Players>) request.getAttribute("players");

            if (players != null && !players.isEmpty()) {
        %>
        <div class="overflow-x-auto">
            <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                <thead>
                    <tr class="bg-blue-600 text-white">
                        <th class="px-6 py-3 text-left text-sm font-semibold">Player Name</th>
                        <th class="px-6 py-3 text-left text-sm font-semibold">Position</th>
                        <th class="px-6 py-3 text-left text-sm font-semibold">TeamID</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Players player : players) {
                    %>
                    <tr class="border-b hover:bg-gray-100">
                        <td class="px-6 py-4 text-sm"><%= player.getName() %></td>
                        <td class="px-6 py-4 text-sm"><%= player.getPosition() %></td>
                        <td class="px-6 py-4 text-sm"><%= player.getTeamID() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        
        
		
		
        <%
            } else {
        %>
        <p class="text-center text-gray-600">No players available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
