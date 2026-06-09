package uk.ac.ucl.servlets;

import java.io.IOException;
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

@WebServlet("/patientPage")
public class ViewPatientServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  { //retrieve patient id from the patient name the user clicks on in the patient list, forward to specific patient data page (patientPage.jsp)
    try
    {
      String id = request.getParameter("id");
      if (id == null)
      {
        throw new IOException("invalid patient id");
      }

      Model model = ModelFactory.getModel();

      Map<String, String> patient = model.getPatientById(id);
      if (patient == null)
      {
        throw new IOException("patient not found");
      }
      request.setAttribute("patient", patient);

      //go to specific patient data page (patientPage.jsp)
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/patientPage.jsp");
      dispatch.forward(request, response);
    }
    catch (IOException e)
    {
      request.setAttribute("errorMessage", "Error loading data: " + e.getMessage());
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
      dispatch.forward(request, response);
    }
  }
}
