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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //go to search.jsp for search page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/search.jsp");
        dispatch.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //get search parameters from user input in search.jsp
        String searchString = request.getParameter("searchstring");
        String status       = request.getParameter("status");
        String gender       = request.getParameter("gender");
        String orderBy      = request.getParameter("orderBy");

        try {
            Model model = ModelFactory.getModel();
            List<Map<String, String>> searchResult = model.searchPatients(searchString, gender, status, orderBy);
            //forward search results to searchResult.jsp to display results to user
            request.setAttribute("result", searchResult);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
            dispatch.forward(request, response);
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error searching: " + e.getMessage()); //if there is an error, go to error.jsp w/ error message
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }
}