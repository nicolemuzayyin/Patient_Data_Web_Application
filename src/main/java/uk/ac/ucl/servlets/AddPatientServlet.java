package uk.ac.ucl.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //forward the request to addPatient.jsp
        getServletContext().getRequestDispatcher("/addPatient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //retrieve form data to create a new patient and add to the model
        String first = request.getParameter("FIRST");
        String last  = request.getParameter("LAST");

        if (first == null || first.trim().isEmpty() || last == null || last.trim().isEmpty()) {
            request.setAttribute("errorMessage", "First name and last name are required.");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        String[] columns = {
            "BIRTHDATE", "DEATHDATE", "SSN", "DRIVERS", "PASSPORT", "PREFIX",
            "FIRST", "LAST", "SUFFIX", "MAIDEN", "MARITAL", "RACE", "ETHNICITY",
            "GENDER", "BIRTHPLACE", "ADDRESS", "CITY", "STATE", "ZIP"
        };

        //create patient data map for columns for the new patient and generate a unique UUID
        Map<String, String> data = new HashMap<>();
        data.put("ID", UUID.randomUUID().toString());
        for (String column : columns) {
            String val = request.getParameter(column);
            if (val != null) {
                data.put(column, val.trim());
            } else {
                data.put(column, "");
            }
        }

        try {
            //add new patient to model
            Model model = ModelFactory.getModel();
            model.addPatient(data);
            response.sendRedirect("patientList"); //redirect to patientList page
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error saving patient: " + e.getMessage()); //if there is an error, go to error.jsp w/ error message
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
