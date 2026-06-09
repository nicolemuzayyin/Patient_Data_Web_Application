<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <% Map<String, String> patient = (Map<String, String>) request.getAttribute("patient");
     boolean isDeceased = false;
     if (patient != null) {
       isDeceased = patient.get("DEATHDATE") != null && !patient.get("DEATHDATE").isEmpty();
  %>

  <h2 class="title">Patient Details</h2>

  <div>
    <a href="patientList" class="button-link">Back to Patient List</a>
    <a href="editPatient?id=<%= patient.get("ID") %>" class="button-link">Edit</a>
    <form method="POST" action="deletePatient" style="display:inline;"
        onsubmit="return confirm('Delete this patient? This cannot be undone.')">
      <input type="hidden" name="id" value="<%= patient.get("ID") %>"/>
      <input type="submit" value="Delete"/>
    </form>
  </div>

  <table>
    <tbody>
      <tr>
        <th>Name</th>
        <td>
          <% String prefixName = "";
             if (patient.get("PREFIX") != null && !patient.get("PREFIX").isEmpty()) {
               prefixName = patient.get("PREFIX") + " ";
             }
             String suffixName = "";
             if (patient.get("SUFFIX") != null && !patient.get("SUFFIX").isEmpty()) {
               suffixName = ", " + patient.get("SUFFIX");
             }
          %>
          <%= prefixName %><%= patient.get("FIRST") %> <%= patient.get("LAST") %><%= suffixName %>
        </td>
      </tr>
      <% String statusLabel;
         if (isDeceased) {
           statusLabel = "Deceased";
         } else {
           statusLabel = "Living";
         }
      %>
      <tr><th>Status</th><td><%= statusLabel %></td></tr>
      <% String genderLabel = "";
         if (patient.get("GENDER") != null && !patient.get("GENDER").isEmpty()) {
           if ("M".equals(patient.get("GENDER"))) {
             genderLabel = "Male";
           } else {
             genderLabel = "Female";
           }
         }
      %>
      <tr><th>Gender</th><td><%= genderLabel %></td></tr>
      <tr><th>Patient ID</th><td><%= patient.get("ID") %></td></tr>
      <% String birthdateLabel = "";
         if (patient.get("BIRTHDATE") != null && !patient.get("BIRTHDATE").isEmpty()) {
           birthdateLabel = patient.get("BIRTHDATE");
         }
      %>
      <tr><th>Date of Birth</th><td><%= birthdateLabel %></td></tr>
      <% String deathdateLabel = "";
         if (isDeceased) {
           deathdateLabel = patient.get("DEATHDATE");
         }
      %>
      <tr><th>Date of Death</th><td><%= deathdateLabel %></td></tr>
      <% String birthplaceLabel = "";
         if (patient.get("BIRTHPLACE") != null && !patient.get("BIRTHPLACE").isEmpty()) {
           birthplaceLabel = patient.get("BIRTHPLACE");
         }
      %>
      <tr><th>Birthplace</th><td><%= birthplaceLabel %></td></tr>
      <% String maritalLabel = "";
         if (patient.get("MARITAL") != null && !patient.get("MARITAL").isEmpty()) {
           maritalLabel = patient.get("MARITAL");
         }
      %>
      <tr><th>Marital Status</th><td><%= maritalLabel %></td></tr>
      <% String raceLabel = "";
         if (patient.get("RACE") != null && !patient.get("RACE").isEmpty()) {
           raceLabel = patient.get("RACE");
         }
         String ethnicityLabel = "";
         if (patient.get("ETHNICITY") != null && !patient.get("ETHNICITY").isEmpty()) {
           ethnicityLabel = patient.get("ETHNICITY");
         }
         String raceEthnicitySeparator = "";
         if (!raceLabel.isEmpty() && !ethnicityLabel.isEmpty()) {
           raceEthnicitySeparator = " / ";
         }
      %>
      <tr><th>Race / Ethnicity</th><td><%= raceLabel %><%= raceEthnicitySeparator %><%= ethnicityLabel %></td></tr>
      <% String maidenLabel = "";
         if (patient.get("MAIDEN") != null && !patient.get("MAIDEN").isEmpty()) {
           maidenLabel = patient.get("MAIDEN");
         }
      %>
      <tr><th>Maiden Name</th><td><%= maidenLabel %></td></tr>
      <% String ssnLabel = "";
         if (patient.get("SSN") != null && !patient.get("SSN").isEmpty()) {
           ssnLabel = patient.get("SSN");
         }
      %>
      <tr><th>SSN</th><td><%= ssnLabel %></td></tr>
      <% String passportLabel = "";
         if (patient.get("PASSPORT") != null && !patient.get("PASSPORT").isEmpty()) {
           passportLabel = patient.get("PASSPORT");
         }
      %>
      <tr><th>Passport</th><td><%= passportLabel %></td></tr>
      <% String driversLabel = "";
         if (patient.get("DRIVERS") != null && !patient.get("DRIVERS").isEmpty()) {
           driversLabel = patient.get("DRIVERS");
         }
      %>
      <tr><th>Drivers License</th><td><%= driversLabel %></td></tr>
      <% String addressLabel = "";
         if (patient.get("ADDRESS") != null && !patient.get("ADDRESS").isEmpty()) {
           addressLabel = patient.get("ADDRESS");
         }
         String cityLabel = "";
         if (patient.get("CITY") != null && !patient.get("CITY").isEmpty()) {
           cityLabel = ", " + patient.get("CITY");
         }
         String stateLabel = "";
         if (patient.get("STATE") != null && !patient.get("STATE").isEmpty()) {
           stateLabel = ", " + patient.get("STATE");
         }
         String zipLabel = "";
         if (patient.get("ZIP") != null && !patient.get("ZIP").isEmpty()) {
           zipLabel = " " + patient.get("ZIP");
         }
      %>
      <tr><th>Address</th><td><%= addressLabel %><%= cityLabel %><%= stateLabel %><%= zipLabel %></td></tr>
    </tbody>
  </table>

  <% } %>
</div>
</body>
</html>