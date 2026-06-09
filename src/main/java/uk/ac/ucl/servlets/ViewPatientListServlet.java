package uk.ac.ucl.servlets;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/patientList")
public class ViewPatientListServlet extends HttpServlet
{

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  { //retrieve patient data from model to display list of patients
    try {
      Model model = ModelFactory.getModel();


      List<Map<String, String>> patients = model.getAllPatients();
      request.setAttribute("patients", patients);

      ServletContext context = getServletContext(); 
      RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp"); //go to patientList.jsp to display patient data
      dispatch.forward(request, response);

    } catch (IOException e) {
      request.setAttribute("errorMessage", "Error loading data: " + e.getMessage()); //if there is an error, go to error.jsp w/ error message
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
      dispatch.forward(request, response);
    }
  }
}
