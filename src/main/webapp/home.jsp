<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Patient Data App</title>
  <link rel="stylesheet" type="text/css" href="/styles.css">
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2 class="title">Welcome to the Patient Data App</h2>
  <ul>
    <li><a href="patientList">Patient List</a></li>
    <li><a href="search">Search</a></li>
    <li><a href="stats">Statistics</a></li>
  </ul>
</div>
</body>
</html>