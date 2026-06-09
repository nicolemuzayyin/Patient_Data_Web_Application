package uk.ac.ucl.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
    // retrieve patient data from model to calculate statistics + create graphs
    ServletContext context = getServletContext();
    
    try {  
      Model model = ModelFactory.getModel();

      request.setAttribute("cityCount", model.getPatientCountByCity());

      // requirement 10 --> displaying charts

      // gender distribution pie chart
      Map<String, Integer> genderCount = model.getPatientCountByGender();
      int male = 0;
      int female = 0;
      if (genderCount != null) {
        male = genderCount.getOrDefault("Male", 0);
        female = genderCount.getOrDefault("Female", 0);
      }
      int totalGender = male + female;

      // calculate percentages of male and female
      int malePct = 0;
      if (totalGender > 0) {
        malePct = (int) Math.round(male * 100.0 / totalGender);
      }
      int femalePct = 100 - malePct; // guarantees male + female always add up to exactly 100

      request.setAttribute("maleCount", male);
      request.setAttribute("femaleCount", female);
      request.setAttribute("malePct", malePct);
      request.setAttribute("femalePct", femalePct);

      // age distribution bar chart
      Map<String, Integer> ageGroups = model.getPatientCountByAgeGroup();

      // find the tallest bar to scale all other bars relative to it
      int maxCount = 0; 
      if (ageGroups != null && !ageGroups.isEmpty()) {
        maxCount = Collections.max(ageGroups.values());
      }
      request.setAttribute("ageGroups", ageGroups);
      request.setAttribute("maxCount", maxCount);

      //if user saves JSON file, show success message on statistics page
      String savedParam = request.getParameter("saved");
      if (savedParam != null && savedParam.equals("json")) {
        request.setAttribute("savedJson", true);
      }
      RequestDispatcher dispatch = context.getRequestDispatcher("/stats.jsp");
      dispatch.forward(request, response);
    } catch (Exception e) {
      request.setAttribute("errorMessage", "Error loading data: " + e.getMessage());
      RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
      dispatch.forward(request, response);
    }
  }
}