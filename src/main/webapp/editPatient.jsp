<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ page import="java.util.Map" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Edit Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

  <h2 class="title">Edit Patient</h2>

  <% Map<String, String> patient = (Map<String, String>) request.getAttribute("patient"); %>
  <form method="POST" action="editPatient">
    <input type="hidden" name="id" value="<%= patient.get("ID") %>"/>
    <table>
      <% String birthdateValue = "";
         if (patient.get("BIRTHDATE") != null) {
           birthdateValue = patient.get("BIRTHDATE");
         }
      %>
      <tr><td><label for="BIRTHDATE">BIRTHDATE</label></td><td><input type="text" name="BIRTHDATE" id="BIRTHDATE" value="<%= birthdateValue %>" placeholder="YYYY-MM-DD"/></td></tr>
      <% String deathdateValue = "";
         if (patient.get("DEATHDATE") != null) {
           deathdateValue = patient.get("DEATHDATE");
         }
      %>
      <tr><td><label for="DEATHDATE">DEATHDATE</label></td><td><input type="text" name="DEATHDATE" id="DEATHDATE" value="<%= deathdateValue %>" placeholder="YYYY-MM-DD"/></td></tr>
      <% String ssnValue = "";
         if (patient.get("SSN") != null) {
           ssnValue = patient.get("SSN");
         }
      %>
      <tr><td><label for="SSN">SSN</label></td><td><input type="text" name="SSN" id="SSN" value="<%= ssnValue %>" placeholder="999-XX-XXXX"/></td></tr>
      <% String driversValue = "";
         if (patient.get("DRIVERS") != null) {
           driversValue = patient.get("DRIVERS");
         }
      %>
      <tr><td><label for="DRIVERS">DRIVERS</label></td><td><input type="text" name="DRIVERS" id="DRIVERS" value="<%= driversValue %>"/></td></tr>
      <% String passportValue = "";
         if (patient.get("PASSPORT") != null) {
           passportValue = patient.get("PASSPORT");
         }
      %>
      <tr><td><label for="PASSPORT">PASSPORT</label></td><td><input type="text" name="PASSPORT" id="PASSPORT" value="<%= passportValue %>"/></td></tr>
      <% String prefixValue = "";
         if (patient.get("PREFIX") != null) {
           prefixValue = patient.get("PREFIX");
         }
      %>
      <tr><td><label for="PREFIX">PREFIX</label></td><td><input type="text" name="PREFIX" id="PREFIX" value="<%= prefixValue %>" placeholder="e.g. Mr., Mrs., Dr."/></td></tr>
      <% String firstValue = "";
         if (patient.get("FIRST") != null) {
           firstValue = patient.get("FIRST");
         }
      %>
      <tr><td><label for="FIRST">FIRST</label></td><td><input type="text" name="FIRST" id="FIRST" value="<%= firstValue %>" placeholder="First name"/></td></tr>
      <% String lastValue = "";
         if (patient.get("LAST") != null) {
           lastValue = patient.get("LAST");
         }
      %>
      <tr><td><label for="LAST">LAST</label></td><td><input type="text" name="LAST" id="LAST" value="<%= lastValue %>" placeholder="Last name"/></td></tr>
      <% String suffixValue = "";
         if (patient.get("SUFFIX") != null) {
           suffixValue = patient.get("SUFFIX");
         }
      %>
      <tr><td><label for="SUFFIX">SUFFIX</label></td><td><input type="text" name="SUFFIX" id="SUFFIX" value="<%= suffixValue %>" placeholder="e.g. Jr., III"/></td></tr>
      <% String maidenValue = "";
         if (patient.get("MAIDEN") != null) {
           maidenValue = patient.get("MAIDEN");
         }
      %>
      <tr><td><label for="MAIDEN">MAIDEN</label></td><td><input type="text" name="MAIDEN" id="MAIDEN" value="<%= maidenValue %>" placeholder="If applicable"/></td></tr>
      <% String maritalSelectedM = "";
         String maritalSelectedS = "";
         if ("M".equals(patient.get("MARITAL"))) {
           maritalSelectedM = "selected";
         }
         if ("S".equals(patient.get("MARITAL"))) {
           maritalSelectedS = "selected";
         }
      %>
      <tr><td><label for="MARITAL">MARITAL</label></td><td>
        <select name="MARITAL" id="MARITAL">
          <option value="">—</option>
          <option value="M" <%= maritalSelectedM %>>Married</option>
          <option value="S" <%= maritalSelectedS %>>Single</option>
        </select>
      </td></tr>
      <% String raceValue = "";
         if (patient.get("RACE") != null) {
           raceValue = patient.get("RACE");
         }
      %>
      <tr><td><label for="RACE">RACE</label></td><td><input type="text" name="RACE" id="RACE" value="<%= raceValue %>" placeholder="e.g. white, black, asian"/></td></tr>
      <% String ethnicityValue = "";
         if (patient.get("ETHNICITY") != null) {
           ethnicityValue = patient.get("ETHNICITY");
         }
      %>
      <tr><td><label for="ETHNICITY">ETHNICITY</label></td><td><input type="text" name="ETHNICITY" id="ETHNICITY" value="<%= ethnicityValue %>" placeholder="e.g. english, french"/></td></tr>
      <% String genderSelectedM = "";
         String genderSelectedF = "";
         if ("M".equals(patient.get("GENDER"))) {
           genderSelectedM = "selected";
         }
         if ("F".equals(patient.get("GENDER"))) {
           genderSelectedF = "selected";
         }
      %>
      <tr><td><label for="GENDER">GENDER</label></td><td>
        <select name="GENDER" id="GENDER">
          <option value="">—</option>
          <option value="M" <%= genderSelectedM %>>Male</option>
          <option value="F" <%= genderSelectedF %>>Female</option>
        </select>
      </td></tr>
      <% String birthplaceValue = "";
         if (patient.get("BIRTHPLACE") != null) {
           birthplaceValue = patient.get("BIRTHPLACE");
         }
      %>
      <tr><td><label for="BIRTHPLACE">BIRTHPLACE</label></td><td><input type="text" name="BIRTHPLACE" id="BIRTHPLACE" value="<%= birthplaceValue %>" placeholder="City Country (Do not add comma between)"/></td></tr>
      <% String addressValue = "";
         if (patient.get("ADDRESS") != null) {
           addressValue = patient.get("ADDRESS");
         }
      %>
      <tr><td><label for="ADDRESS">ADDRESS</label></td><td><input type="text" name="ADDRESS" id="ADDRESS" value="<%= addressValue %>" placeholder="Street address"/></td></tr>
      <% String cityValue = "";
         if (patient.get("CITY") != null) {
           cityValue = patient.get("CITY");
         }
      %>
      <tr><td><label for="CITY">CITY</label></td><td><input type="text" name="CITY" id="CITY" value="<%= cityValue %>"/></td></tr>
      <% String stateValue = "";
         if (patient.get("STATE") != null) {
           stateValue = patient.get("STATE");
         }
      %>
      <tr><td><label for="STATE">STATE</label></td><td><input type="text" name="STATE" id="STATE" value="<%= stateValue %>"/></td></tr>
      <% String zipValue = "";
         if (patient.get("ZIP") != null) {
           zipValue = patient.get("ZIP");
         }
      %>
      <tr><td><label for="ZIP">ZIP</label></td><td><input type="text" name="ZIP" id="ZIP" value="<%= zipValue %>"/></td></tr>
    </table>
    <br/>
    <input type="submit" value="Save Changes" class="button-link"/>
    <a href="patientPage?id=<%= patient.get("ID") %>" class="button-link">Cancel</a>
  </form>
</div>
</body>
</html>