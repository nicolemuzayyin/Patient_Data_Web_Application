<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="stylesheet" type="text/css" href="/styles.css"/>
<title>Statistics</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

<h2 class="title">Patient Statistics</h2>
<a href="saveJson" class="button-link">Save as JSON</a> <%-- button to trigger saving JSON file --%>

<%
  Boolean savedJson = (Boolean) request.getAttribute("savedJson");
  if (Boolean.TRUE.equals(savedJson)) {
%>
  <p class="success">Data saved to data/patients.json</p> <%-- display success message for saving JSON file --%>
<% } %>

<div class="chart-grid">

  <%-- requirement 10 -> displaying graphs --%>

  <%-- graph 1 -> gender distribution pie chart --%>
  <div class="chart-card">
    <h3>Gender Distribution</h3>
    <%
      int male=(Integer) request.getAttribute("maleCount");
      int female=(Integer) request.getAttribute("femaleCount");
      int malePct=(Integer) request.getAttribute("malePct");
      int femalePct=(Integer) request.getAttribute("femalePct");
    %>
    <div class="pie-chart-wrapper">
      <div class="pie-chart" style="background: conic-gradient(#1976d2 0% <%= malePct %>%, #e91e8c <%= malePct %>% 100%)"></div> <%-- conic gradient to create pie chart based on gender percentages --%>
    </div>
    <div class="chart-legend">
      <span><div class="legend-color male-color"></div> Male (<%= male %>) — <%= malePct %>%</span>
      <span><div class="legend-color female-color"></div> Female (<%= female %>) — <%= femalePct %>%</span>
    </div>
  </div>

  <%-- graph 2 -> age distribution bar chart --%>
  <div class="chart-card">
    <h3>Age Distribution</h3>
    <div class="bar-chart-wrapper">
      <%
        Map<String, Integer> ageGroups=(Map<String, Integer>) request.getAttribute("ageGroups");
        int maxCount=(Integer) request.getAttribute("maxCount"); // retrieve highest count for any age group
      %>
      <div class="bar-chart">
        <% if (ageGroups != null && !ageGroups.isEmpty()) {
             for (Map.Entry<String, Integer> entry : ageGroups.entrySet()) {
               int val = entry.getValue();
               double barHeight = maxCount > 0 ? (val * 200.0 / maxCount) : 0; //bar height is scaled relative to the tallest bar
        %>
        <div class="bar-container">
          <div class="bar-value"><%= val %></div>
          <div class="bar" style="height: <%= String.format("%.0f", barHeight) %>px"></div> <%-- draw each bar in chart--%>
          <div class="bar-label"><%= entry.getKey() %></div>
        </div>
        <% } } %>
      </div>
    </div>
  </div>

</div>
<%-- displaying table of patients by city --%>
<h3>Patients by City</h3>
<table>
  <thead>
    <tr><th>City</th><th>Patients</th></tr>
  </thead>
  <tbody>
    <%
      Map<String, Integer> cityCount=(Map<String, Integer>) request.getAttribute("cityCount");
      if (cityCount != null) {
        for (Map.Entry<String, Integer> entry : cityCount.entrySet()) {
    %>
    <tr>
      <td><%= entry.getKey() %></td>
      <td><%= entry.getValue() %></td>
    </tr>
    <% } } %>
  </tbody>
</table>

</div>
</body>
</html>