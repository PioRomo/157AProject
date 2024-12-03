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
    <div class="min-h-screen bg-white">
      <div class="relative min-h-screen bg-black flex items-center pt-0 pb-0 pr-64 overflow-hidden bg-gradient-to-tl from-cyan-300 from-25% via-blue-500 via-50% to-blue-600 to-80%">
        <div class="max-w-xl mt-0 mb-0 mr-32 ml-32 w-full relative z-1">
          <h1 class="text-7xl text-white font-bold mb-32">
            <span>The premiere</span>
            <span>basketball manager.</span>
          </h1>
          <div class="text-4xl text-white mb-48">
            Welcome to the League Management System.
            Manage your league with ease like a pro!
          </div>
          <div class="flex gap-16">
            <a href="${pageContext.request.contextPath}/"></a>
            <a href="games" class="border-white border-2 hover:bg-gray-100 rounded text-center text-white hover:text-black px-10 py-3 text-lg font-medium">Organize Games</a>
            <a href="teams" class="border-white border-2 hover:bg-gray-100 rounded text-center text-white hover:text-black px-10 py-3 text-lg font-medium">View Teams</a>
            <a href="players" class="border-white border-2 hover:bg-gray-100 rounded text-center text-white hover:text-black px-10 py-3 text-lg font-medium">View Players</a>
            <a href="stats" class="border-white border-2 hover:bg-gray-100 rounded text-center text-white hover:text-black px-10 py-3 text-lg font-medium">View Statistics</a>
          </div>
        </div>
      </div>

      <div class="p-20 flex justify-center gap-4 bg-white">
        <div class="text-center p-2 rounded-2xl bg-white flex-1 max-w-72">
          <div class="text-6xl font-bold text-black mb-2">15</div>
          <div class="text-2xl leading-none text-black mb-4">teams</div>
          <div class="mb-4 text-grey-700 leading-6">Sample size for testing</div>
        </div>
        <div class="text-center p-2 rounded-2xl bg-white flex-1 max-w-72">
          <div class="text-6xl font-bold text-black mb-2">45</div>
          <div class="text-2xl leading-none text-black mb-4">players</div>
          <div class="mb-4 text-grey-700 leading-6">Three per team</div>
        </div>
        <div class="text-center p-2 rounded-2xl bg-white flex-1 max-w-72">
          <div class="text-6xl font-bold text-black mb-2">15</div>
          <div class="text-2xl leading-none text-black mb-4">games</div>
          <div class="mb-4 text-grey-700 leading-6">Results displayed by default</div>
        </div>
      </div>
    </div>

</body>
</html>
