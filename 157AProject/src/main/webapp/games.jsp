<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.league_management.model.Games" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Games</title>
    <script src="https://cdn.tailwindcss.com"></script> <!-- Tailwind CSS -->
</head>
<body class="bg-gradient-to-tl from-indigo-300 from-25% via-blue-500 via-50% to-blue-600 to-80%">
	<%@ include file="navbar.jsp" %>
	
    <div class="container mx-auto py-8">
    	<div class="align-center justify-center">
	        <h1 class="text-3xl font-bold text-center mb-8 text-white ">Games</h1>
    	</div>
        <%
            // Retrieve the list of games from the request attribute
            List<Games> games = (List<Games>) request.getAttribute("games");

            if (games != null && !games.isEmpty()) {
        %>
        
        
        <div class="flex">
        
				<!-- games.jsp -->
		<div class="p-5 basis-1/5 justify-center align-center">
			<!-- games.jsp -->
			<form action="games" method="POST" class="max-w-md mx-auto bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 mt-8">
	    		<h2 class="text-2xl font-bold mb-4 text-center">Add Game</h2>
	
	    		<!-- Home Team ID -->
	    		<div class="mb-4">
	        		<label for="homeTeamID" class="block text-gray-700 text-sm font-bold mb-2">Home Team ID</label>
	        		<input type="number" id="homeTeamID" name="homeTeamID" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
	    		</div>
	
	    		<!-- Away Team ID -->
	    		<div class="mb-4">
	        		<label for="awayTeamID" class="block text-gray-700 text-sm font-bold mb-2">Away Team ID</label>
	        		<input type="number" id="awayTeamID" name="awayTeamID" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
	    		</div>
	
	    		<!-- Home Team Score -->
	    		<div class="mb-4">
	        		<label for="homeTeamScore" class="block text-gray-700 text-sm font-bold mb-2">Home Team Score</label>
	        		<input type="number" id="homeTeamScore" name="homeTeamScore" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
	    		</div>
	
	    		<!-- Away Team Score -->
	    		<div class="mb-4">
	        		<label for="awayTeamScore" class="block text-gray-700 text-sm font-bold mb-2">Away Team Score</label>
	        		<input type="number" id="awayTeamScore" name="awayTeamScore" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
	    		</div>
	
	    		<!-- Submit Button -->
	    		<div class="flex items-center justify-center">
	        		<button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
	            		Add Game
	        		</button>
	    		</div>
			</form>
		</div>    
            
        
		<div class="p-5 basis-4/5 justify-center align-center">
            <div class="overflow-x-auto">
	            <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
	                <thead>
	                    <tr class="bg-green-600 text-white">
	                        <th class="px-6 py-3 text-left text-sm font-semibold">Game ID</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">HomeTeamID</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">AwayTeamID</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">HomeTeamScore</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">AwayTeamScore</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <%
	                        for (Games game : games) {
	                    %>
	                    <tr class="border-b hover:bg-gray-100">
	                        <td class="px-6 py-4 text-sm"><%= game.getGameID() %></td>
	                        <td class="px-6 py-4 text-sm"><%= game.getHomeTeamID() %></td>
	                        <td class="px-6 py-4 text-sm"><%= game.getAwayTeamID() %></td>
	                        <td class="px-6 py-4 text-sm"><%= game.getHomeTeamScore() %></td>
	                        <td class="px-6 py-4 text-sm"><%= game.getAwayTeamScore() %></td>
	                        
	                    </tr>
	                    <%
	                        }
	                    %>
	                </tbody>
	            </table>
        	</div>
    	</div>
        </div>
        <%
            } else {
        %>
        <p class="text-center text-gray-600">No games scheduled.</p>
        <%
            }
        %>
    </div>
</body>
</html>
