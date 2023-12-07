package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dao.UtenteDao;
import model.Dto.EvalCountDto;
import services.UtenteService;
import utils.DBConnection;

@WebServlet("/distributeEvaluators")
public class DistributeEvaluatorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();

        try {
            evaluators = new UtenteService(new UtenteDao(DBConnection.createConnection())).getEvaluatorsOccupiedFree(3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        String mock = "I see you!";
        session.setAttribute("flg", mock);
        /*
         * List<CountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
         * List<CountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");
         */
        List<EvalCountDto> evaluatorsOccupied = new ArrayList<>();
        List<EvalCountDto> evaluatorsFree = new ArrayList<>();

        // Add mock data to evaluatorsOccupied
        EvalCountDto mockOccupied = new EvalCountDto(1, "OccupiedEvaluator1", "Last1", "occupied1@example.com", 8);
        evaluatorsOccupied.add(mockOccupied);

        // Add mock data to evaluatorsFree
        EvalCountDto mockFree = new EvalCountDto(2, "FreeEvaluator1", "Last2", "free1@example.com", 3);
        evaluatorsFree.add(mockFree);

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);

        response.sendRedirect("distribute_evaluators.jsp");
    }
}