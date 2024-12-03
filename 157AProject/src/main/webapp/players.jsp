<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.league_management.model.Players" %>
<%@ page import="com.league_management.model.Team" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Players</title>
    <script src="https://cdn.tailwindcss.com"></script> <!-- Tailwind CSS -->
</head>
<body class="bg-gradient-to-tl from-purple-200 from-25% via-purple-700 via-50% to-indigo-500 to-80% min-h-screen">
	<%@ include file="navbar.jsp" %>
    <div class="container mx-auto py-8">
        <h1 class="text-3xl font-bold text-center mb-8 text-white">Players</h1>

        <%
            // Retrieve the list of players from the request attribute
            List<Players> players = (List<Players>) request.getAttribute("players");
        	List<Team> teams = (List<Team>) request.getAttribute("teams");
            if (players != null && !players.isEmpty()) {
        %>
        
        
       <div class="flex">
	        <div class="p-5 basis-1/5 justify-center align-center">
	       		<div class="bg-white shadow-md rounded-lg p-6 gap-6" id="updateForm">
					<h2 class="text-xl font-semibold mb-4">Update a Player's Details</h2>
			    		<form action="players" method="POST" class="space-y-4">
			        		<!-- Method Spoofing for UPDATE -->
			        		<input type="hidden" name="action" id="action" value="update">
		        
		        		<!-- Team ID Input -->
		            		<label for="id" class="block text-sm font-medium text-gray-700">Player ID</label>
		            		<input 
		                		type="number" 
		                		name="id" 
		                		id="id" 
		                		class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" 
		                		placeholder="Enter Player ID" 
		                		required
		            		>
		            		<label for="name" class="block text-sm font-medium text-gray-700">Player Name</label>
		            		<input 
		                		type="text" 
		                		name="name" 
		                		id="name" 
		                		class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" 
		                		placeholder="Enter Player Name" 
		                		required
		            		>
		            		<label for="position" class="block text-sm font-medium text-gray-700">Player Position</label>
		            		<input 
		                		type="text" 
		                		name="position" 
		                		id="position" 
		                		class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" 
		                		placeholder="Enter Player Position" 
		                		list="position-types" 
		                		required
		            		>
		            		<datalist id="position-types">
		                    	<option value="Forward" />
		                    	<option value="Guard" />
		                    	<option value="Center" />
		                    </datalist>
		
		        		<!-- Update Button -->
		        		<button 
		            		type="submit" 
		            		class="w-full bg-blue-500 text-white rounded-lg px-4 py-2 hover:bg-blue-600">
		            		Update Player Details
		        		</button>
		    		</form>
	       		</div>
			</div>    
	        
	        <div class="p-5 basis-4/5 justify-center align-center">
		        <div class="overflow-x-auto">
		            <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
		                <thead>
		                    <tr class="bg-blue-600 text-white">
		                    	<th class="px-6 py-3 text-left text-sm font-semibold">Player ID</th>
		                        <th class="px-6 py-3 text-left text-sm font-semibold">Player Name</th>
		                        <th class="px-6 py-3 text-left text-sm font-semibold">Position</th>
		                        <th class="px-6 py-3 text-left text-sm font-semibold">Team ID</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <%
		                        for (Players player : players) {
		                    %>
		                    <tr class="border-b hover:bg-gray-100">
		                    	<td class="px-6 py-4 text-sm"><%= player.getPlayerID() %></td>
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
       		</div>
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
