package utils.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import model.dto.UtenteDto;
import services.user.ListService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(
        name = "DistributeEvaluatorsServlet",
        urlPatterns = {"/distribute_evaluators"}
)
public class DistributeEvaluatorsServlet extends HttpServlet {

    public DistributeEvaluatorsServlet() {
        super();
    }


    /**
     * Handles the HTTP GET method for displaying evaluators.
     * This method is called when the servlet receives a GET request.
     * It fetches all evaluators and their corresponding users from the database
     * using {@link services.user.listService#getEvaluatorsOccupiedFree(int)}.
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

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        List<UtenteDto> waitingList = new ArrayList<>();

        int soglia = Integer.parseInt(request.getParameter("soglia1"));
        int sogliaAvg;

        try {
            ListService listService = new ListService(new UtenteDao());
            evaluators = listService.getEvaluatorsOccupiedFree(soglia);
            sogliaAvg = listService.getAverageValued();
            waitingList = listService.getWaitingList();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        List<EvalCountDto> evaluatorsOccupied = evaluators.get("occupati");
        List<EvalCountDto> evaluatorsFree = evaluators.get("disponibili");

        request.setAttribute("waiting", waitingList);
        request.setAttribute("occupati", evaluatorsOccupied);
        request.setAttribute("disponibili", evaluatorsFree);

        request.getRequestDispatcher("distribute_evaluators.jsp").forward(request, response);

    }


    /**
     * Handles the HTTP POST method for rearranging evaluators.
     * This method is called when the servlet receives a POST request.
     * It fetches all evaluators and their corresponding users from the database.
     * It then calls the {@link services.user.listService#distributeValutatori(HashMap, int)}.
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

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        List<UtenteDto> waitingList = new ArrayList<>();

        int soglia = Integer.parseInt(request.getParameter("soglia2"));

        int res = 0;
        try {
            ListService listService = new ListService(new UtenteDao());
            evaluators = listService.getEvaluatorsOccupiedFree(soglia);
            res = listService.distributeValutatori(evaluators, soglia);
            evaluators = listService.getEvaluatorsOccupiedFree(soglia);
            waitingList = listService.getWaitingList();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        List<EvalCountDto> evaluatorsOccupied = evaluators.get("occupati");
        List<EvalCountDto> evaluatorsFree = evaluators.get("disponibili");

        request.setAttribute("res", res);
        request.setAttribute("occupati", evaluatorsOccupied);
        request.setAttribute("disponibili", evaluatorsFree);
        request.setAttribute("waiting", waitingList);

        request.getRequestDispatcher("distribute_evaluators.jsp").forward(request, response);
    }


}