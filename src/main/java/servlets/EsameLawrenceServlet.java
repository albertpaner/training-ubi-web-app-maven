package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.EvalCountDto;
import services.user.UserLawrenceService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(
        name = "EsameLawrenceServlet",
        urlPatterns = {"/esame_lawrence"}
)
public class EsameLawrenceServlet extends HttpServlet {
    private String SOCIET_OPERATIVA = "Intesa Sao Paolo";

    public EsameLawrenceServlet() {
        super();
    }


    /**
     * Handles the HTTP GET method for displaying evaluators.
     * This method is called when the servlet receives a GET request.
     * It fetches all evaluators and their corresponding users from the database
     * using {@link services.user.UtenteEvaluationService#getEvaluatorsOccupiedFree(int)}.
     * It returns a HashMap where the keys are evaluators and the values are lists of users valued by the evaluator.
     *
     * @param request  The request object that contains the request the client has made of the servlet.
     * @param response The response object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserLawrenceService u = null;
        List<EvalCountDto> list = null;
        try {
            u = new UserLawrenceService();
            u.showUserBySO(SOCIET_OPERATIVA);
             list = u.showUserBySO(SOCIET_OPERATIVA);;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list", list);

            request.getRequestDispatcher("lawrence_esame.jsp").forward(request, response);

    }


    /**
     * Handles the HTTP POST method for rearranging evaluators.
     * This method is called when the servlet receives a POST request.
     * It fetches all evaluators and their corresponding users from the database.
     * It then calls the {@link services.user.UtenteEvaluationService#distributeValutatori(HashMap, int)}.
     * It fetches the evaluators and their corresponding users from the database again and the waiting list.
     * This method is triggered when a button is pressed on the front-end.
     *
     * @param request  The request object that contains the request the client has made of the servlet.
     * @param response The response object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}