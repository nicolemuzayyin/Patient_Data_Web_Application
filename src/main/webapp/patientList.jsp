<%@ page import="java.util.List,java.util.Map,java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>

<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Patient List</title>
</head>
<body>
  <jsp:include page="/header.jsp"/>
  <div class="main">

    <h2 class="title">Patient List</h2>
    <a href="addPatient" class="button-link">Add Patient</a>

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
          List<Map<String, String>> patients = (List<Map<String, String>>) request.getAttribute("patients");
          if (patients != null) {
            for (Map<String, String> patient : patients) {
              String id        = patient.get("ID");
              String firstName = patient.get("FIRST");
              String lastName  = patient.get("LAST");
              String gender    = patient.get("GENDER");
              String birthDate = patient.get("BIRTHDATE");
              String city      = patient.get("CITY");
              String deathDate = patient.get("DEATHDATE");
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
        <%
            }
          }
        %>
      </tbody>
    </table>

  </div>
</body>
</html>