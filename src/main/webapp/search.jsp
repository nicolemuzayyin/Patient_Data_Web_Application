<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="/styles.css"/>
  <title>Search Patients</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

  <h2 class="title">Search Patients</h2>

  <form method="POST" action="search">
    <table>
      <tr>
        <td><label for="searchstring">Keyword</label></td>
        <td><input type="text" name="searchstring" id="searchstring" placeholder="e.g. City, Name, Year of Birth"/></td>
      </tr>
      <tr>
        <td><label for="orderBy">Sort By</label></td>
        <td>
          <select name="orderBy" id="orderBy">
            <option selected value="">None</option>
            <option value="birthdate_asc">Birthdate - Oldest First</option>
            <option value="birthdate_desc">Birthdate - Youngest First</option>
            <option value="lastName_asc">Last Name A → Z</option>
            <option value="lastName_desc">Last Name Z → A</option>
          </select>
        </td>
      </tr>
      <tr>
        <td><label for="gender">Gender</label></td>
        <td>
          <select name="gender" id="gender">
            <option selected value="">All</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
          </select>
        </td>
      </tr>
      <tr>
        <td><label for="status">Status</label></td>
        <td>
          <select name="status" id="status">
            <option selected value="">All</option>
            <option value="living">Living</option>
            <option value="deceased">Deceased</option>
          </select>
        </td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Search" class="button-link"/></td>
      </tr>
    </table>
  </form>

</div>
</body>
</html>