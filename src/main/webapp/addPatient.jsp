<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Add Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

  <h2 class="title">Add New Patient</h2>

  <form method="POST" action="addPatient">
    <table>
      <tr><td><label for="BIRTHDATE">BIRTHDATE</label></td><td><input type="text" name="BIRTHDATE" id="BIRTHDATE" placeholder="YYYY-MM-DD"/></td></tr>
      <tr><td><label for="DEATHDATE">DEATHDATE</label></td><td><input type="text" name="DEATHDATE" id="DEATHDATE" placeholder="YYYY-MM-DD (leave blank if living)"/></td></tr>
      <tr><td><label for="SSN">SSN</label></td><td><input type="text" name="SSN" id="SSN" placeholder="999-XX-XXXX"/></td></tr>
      <tr><td><label for="DRIVERS">DRIVERS</label></td><td><input type="text" name="DRIVERS" id="DRIVERS"/></td></tr>
      <tr><td><label for="PASSPORT">PASSPORT</label></td><td><input type="text" name="PASSPORT" id="PASSPORT"/></td></tr>
      <tr><td><label for="PREFIX">PREFIX</label></td><td><input type="text" name="PREFIX" id="PREFIX" placeholder="e.g. Mr., Mrs., Dr."/></td></tr>
      <tr><td><label for="FIRST">FIRST</label></td><td><input type="text" name="FIRST" id="FIRST" placeholder="First name"/></td></tr>
      <tr><td><label for="LAST">LAST</label></td><td><input type="text" name="LAST" id="LAST" placeholder="Last name"/></td></tr>
      <tr><td><label for="SUFFIX">SUFFIX</label></td><td><input type="text" name="SUFFIX" id="SUFFIX" placeholder="e.g. Jr., III"/></td></tr>
      <tr><td><label for="MAIDEN">MAIDEN</label></td><td><input type="text" name="MAIDEN" id="MAIDEN" placeholder="If applicable"/></td></tr>
      <tr><td><label for="MARITAL">MARITAL</label></td><td>
        <select name="MARITAL" id="MARITAL">
          <option value="">—</option>
          <option value="M">Married</option>
          <option value="S">Single</option>
        </select>
      </td></tr>
      <tr><td><label for="RACE">RACE</label></td><td><input type="text" name="RACE" id="RACE" placeholder="e.g. white, black, asian"/></td></tr>
      <tr><td><label for="ETHNICITY">ETHNICITY</label></td><td><input type="text" name="ETHNICITY" id="ETHNICITY" placeholder="e.g. english, french"/></td></tr>
      <tr><td><label for="GENDER">GENDER</label></td><td>
        <select name="GENDER" id="GENDER">
          <option value="">—</option>
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select>
      </td></tr>
      <tr><td><label for="BIRTHPLACE">BIRTHPLACE</label></td><td><input type="text" name="BIRTHPLACE" id="BIRTHPLACE" placeholder="City Country"/></td></tr>
      <tr><td><label for="ADDRESS">ADDRESS</label></td><td><input type="text" name="ADDRESS" id="ADDRESS" placeholder="Street address"/></td></tr>
      <tr><td><label for="CITY">CITY</label></td><td><input type="text" name="CITY" id="CITY"/></td></tr>
      <tr><td><label for="STATE">STATE</label></td><td><input type="text" name="STATE" id="STATE"/></td></tr>
      <tr><td><label for="ZIP">ZIP</label></td><td><input type="text" name="ZIP" id="ZIP"/></td></tr>
    </table>
    <br/>
    <input type="submit" value="Add Patient" class="button-link"/>
    <a href="patientList" class="button-link">Cancel</a>
  </form>
</div>
</body>
</html>