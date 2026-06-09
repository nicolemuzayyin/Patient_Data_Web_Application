package uk.ac.ucl.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/editPatient")
public class EditPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //
        String id = request.getParameter("id");
        try {
            Model model = ModelFactory.getModel();
            java.util.Map<String, String> patient = model.getPatientById(id);
            if (patient == null) {
                request.setAttribute("errorMessage", "Patient not found.");
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("patient", patient);
            getServletContext().getRequestDispatcher("/editPatient.jsp").forward(request, response);
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //retrieve patient id and data to update the patient's data
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect("patientList");
            return;
        }

        String[] columns = {
            "BIRTHDATE", "DEATHDATE", "SSN", "DRIVERS", "PASSPORT", "PREFIX",
            "FIRST", "LAST", "SUFFIX", "MAIDEN", "MARITAL", "RACE", "ETHNICITY",
            "GENDER", "BIRTHPLACE", "ADDRESS", "CITY", "STATE", "ZIP"
        };

        Map<String, String> data = new HashMap<>();
        for (String column : columns) {
            String val = request.getParameter(column);
            if (val != null) {
                data.put(column, val.trim());
            } else {
                data.put(column, "");
            }
        }

        
        if (data.get("FIRST") == null || data.get("FIRST").isEmpty() ||
            data.get("LAST") == null || data.get("LAST").isEmpty()) {
            request.setAttribute("errorMessage", "First name and last name are required.");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            //update patient data in model
            Model model = ModelFactory.getModel();
            model.updatePatient(id, data);
            response.sendRedirect("patientPage?id=" + java.net.URLEncoder.encode(id, "UTF-8"));
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error saving changes: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
