<%@ page import="java.util.List,java.util.Map,java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Search Results</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

  <h2 class="title">Search Results</h2>
  <a href="/search" class="button-link">New Search</a>

  <% List<Map<String, String>> patients = (List<Map<String, String>>) request.getAttribute("result"); %>
  <% if (patients != null && patients.size() > 0) { %>
    <p><strong><%= patients.size() %></strong> result(s) found</p>
    <table>
      <thead>
        <tr>
          <th>Patient</th>
          <th>Date of Birth</th>
          <th>Gender</th>
          <th>City</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Map<String, String> patient : patients) {
            String id        = (String) patient.get("ID");
            String firstName = (String) patient.get("FIRST");
            String lastName  = (String) patient.get("LAST");
            String gender    = (String) patient.get("GENDER");
            String birthDate = (String) patient.get("BIRTHDATE");
            String city      = (String) patient.get("CITY");
            String deathDate = (String) patient.get("DEATHDATE");
            boolean deceased = deathDate != null && !deathDate.isEmpty();
            String href      = "patientPage?id=" + URLEncoder.encode(id, "UTF-8");
        %>
        <tr>
          <td>
            <a href="<%= href %>"><%= firstName %> <%= lastName %></a><br/>
            <small><%= id %></small>
          </td>
          <% String birthDateDisplay = "––";
             if (birthDate != null && !birthDate.isEmpty()) {
               birthDateDisplay = birthDate;
             }
          %>
          <td><%= birthDateDisplay %></td>
          <% String genderDisplay = "––";
             if (gender != null && !gender.isEmpty()) {
               if ("M".equals(gender)) {
                 genderDisplay = "Male";
               } else {
                 genderDisplay = "Female";
               }
             }
          %>
          <td><%= genderDisplay %></td>
          <% String cityDisplay = "––";
             if (city != null && !city.isEmpty()) {
               cityDisplay = city;
             }
          %>
          <td><%= cityDisplay %></td>
          <% String statusDisplay = "Living";
             if (deceased) {
               statusDisplay = "Deceased";
             }
          %>
          <td><%= statusDisplay %></td>
        </tr>
        <% } %>
      </tbody>
    </table>
  <% } else { %>
    <p>No patients matched your search.</p>
  <% } %>

</div>
</body>
</html>