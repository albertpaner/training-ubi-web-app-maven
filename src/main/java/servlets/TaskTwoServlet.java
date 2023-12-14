package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import model.dto.UtenteDto;
import services.user.UtenteEvaluationService;

@WebServlet(name = "TaskTwo", urlPatterns = { "/task_two" })
public class TaskTwoServlet extends HttpServlet {
    public TaskTwoServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {

	HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
	List<UtenteDto> waitingList = new ArrayList<>();

	int soglia = Integer.parseInt(request.getParameter("soglia1"));
	int sogliaAvg;

	try {
	    UtenteEvaluationService utenteEvaluationService = new UtenteEvaluationService(new UtenteDao());
	    evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(soglia);
	    sogliaAvg = utenteEvaluationService.getAverageValued();
	    waitingList = utenteEvaluationService.getWaitingList();
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

}
