<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
</head>
<body>
<div class="main">
  <h1 class="title">Error</h1>
  <% String errorMessage = (String) request.getAttribute("errorMessage");
     if (errorMessage != null) { %>
    <p class="error"><%= errorMessage %></p>
  <% } %>
  <a href="/" class="button-link">Home</a>
</div>
</body>
</html>