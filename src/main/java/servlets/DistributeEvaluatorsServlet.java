package servlets;

import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.user.UtenteEvaluation;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(
        name = "DistributeEvaluatorsServlet",
        urlPatterns = {"/distribute_evaluators"}
)
public class DistributeEvaluatorsServlet extends HttpServlet {


    /**
     * Handles the HTTP GET method for displaying evaluators.
     * This method is called when the servlet receives a GET request.
     * It fetches all evaluators and their corresponding users from the database.
     * It returns a HashMap where the keys are evaluators and the values are lists of users valued by the evaluator.
     * @param request  The request object that contains the request the client has made of the servlet.
     * @param response The response object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request.
     *
     * @see #fetchEvaluators(int)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        try {
            evaluators = fetchEvaluators(3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        String mock = "I see you!";
        session.setAttribute("flg", mock);

        List<EvalCountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
        List<EvalCountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);

        request.getRequestDispatcher("distribute_evaluators.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST method for rearranging evaluators.
     * This method is called when the servlet receives a POST request.
     * It fetches all evaluators and their corresponding users from the database.
     * It then calls the `rearrengeValutatori` method to rearrange the evaluators.
     * This method is triggered when a button is pressed on the front-end.
     *
     * @param request  The request object that contains the request the client has made of the servlet.
     * @param response The response object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        try {
            evaluators = fetchEvaluators(3);
            UtenteEvaluation utenteEvaluation = new UtenteEvaluation(new UtenteDao());
            utenteEvaluation.rearrengeValutatori(evaluators, 3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        String mock = "I see you!";
        session.setAttribute("flg", mock);

        List<EvalCountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
        List<EvalCountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);

        request.getRequestDispatcher("distribute_evaluators.jsp").forward(request, response);
    }

    /**
     * This method returns a HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili",
     * and the values are lists of EvalCountDto objects.
     *
     * @param soglia The threshold value for the number of users.
     * @return A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     *
     * @see EvalCountDto
     * @see UtenteEvaluation#getEvaluatorsOccupiedFree(int)
     * */
    private HashMap<String, List<EvalCountDto>> fetchEvaluators(int soglia) throws ClassNotFoundException, SQLException {
        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        UtenteEvaluation utenteEvaluation = new UtenteEvaluation(new UtenteDao());
        evaluators = utenteEvaluation.getEvaluatorsOccupiedFree(soglia);
        utenteEvaluation.rearrengeValutatori(evaluators, soglia);

        return evaluators;
    }
}