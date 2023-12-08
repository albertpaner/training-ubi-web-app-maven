package servlets;

import model.Dao.UtenteDao;
import model.Dto.EvalCountDto;
import services.UtenteService;
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

@WebServlet("/distributeEvaluators")
public class DistributeEvaluatorsServlet extends HttpServlet {

    /*
    private UtenteService utenteService;
    // dependency injection??
    public DistributeEvaluatorsServlet() {
        this.utenteService = new UtenteService(new UtenteDao(DBConnection.createConnection()));
    }
    * */

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
         /*
        List<EvalCountDto> evaluatorsOccupied = new ArrayList<>();
        List<EvalCountDto> evaluatorsFree = new ArrayList<>();

        // Add mock data to evaluatorsOccupied
        EvalCountDto mockOccupied = new EvalCountDto(1, "OccupiedEvaluator1", "Last1", "occupied1@example.com", 8);
        evaluatorsOccupied.add(mockOccupied);

        // Add mock data to evaluatorsFree
        EvalCountDto mockFree = new EvalCountDto(2, "FreeEvaluator1", "Last2", "free1@example.com", 3);
        evaluatorsFree.add(mockFree);*/

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);

        request.getRequestDispatcher("distribute_evaluators.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

    private HashMap<String, List<EvalCountDto>> fetchEvaluators(int soglia) throws ClassNotFoundException, SQLException {
        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        UtenteService utenteService = new UtenteService(new UtenteDao(DBConnection.createConnection()));
        evaluators = utenteService.getEvaluatorsOccupiedFree(soglia);
        utenteService.rearrengeValutatori(evaluators, soglia);
        return evaluators;
    }
}