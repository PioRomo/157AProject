<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>League Management</title>
    <!-- Tailwind CSS CDN link -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">

   <%@ include file="navbar.jsp" %>

    <!-- Main Content -->
    <div class="max-w-7xl mx-auto py-12 px-6 sm:px-6 lg:px-8">
        <h1 class="text-4xl font-extrabold text-gray-900 text-center mb-8">Welcome to the League Management System</h1>
        <p class="text-lg text-gray-700 text-center mb-12">Manage your league with ease like a pro!</p>
        
        
        <div class="flex space-x-4 text-center text-white">
    		<div class="bg-red-500 p-4 rounded shadow-md flex-1 hover:bg-red-600">
    			<h2>Manage teams!</h2>
    		</div>
    		<div class="bg-blue-500 p-4 rounded shadow-md flex-1 hover:bg-blue-600">
    			<h2>Manage player statistics!</h2>
    		</div>
    		<div class="bg-yellow-500 p-4 rounded shadow-md flex-1 hover:bg-yellow-600">
    			<h2>Manage games!</h2>
    		</div>
		</div>
        
        
        
    </div>

</body>
</html>
