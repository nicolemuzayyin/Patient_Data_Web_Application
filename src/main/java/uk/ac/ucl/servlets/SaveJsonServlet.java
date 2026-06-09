package uk.ac.ucl.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.dataframe.JSONWriter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

@WebServlet("/saveJson")
public class SaveJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //retrieve patient data from model to save as JSON file
        try {
            Model model = ModelFactory.getModel();
            JSONWriter writer = new JSONWriter(); //write patient data to JSON file in data directory
            writer.write(model.getDataFrame(), "data/patients.json");
            response.sendRedirect("stats?saved=json"); //redirect to statistics page with success message for saving JSON file
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error saving JSON: " + e.getMessage()); //if there is an error, go to error.jsp w/ error message
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
