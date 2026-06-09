package uk.ac.ucl.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //retrieve patient id to be deleted 
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect("patientList"); 
            return;
        }
        try { 
            //delete patient from model
            Model model = ModelFactory.getModel();
            model.deletePatient(id);
            response.sendRedirect("patientList");
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error deleting patient: " + e.getMessage()); //if there is an error, go to error.jsp w/ error messagexs
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
