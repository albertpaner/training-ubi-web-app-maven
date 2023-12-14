package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UtenteDao;
import services.user.EvaluatorDisplayService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "EvaluatorDisplayServlet",
        urlPatterns = {"/display_evaluators"}
)
public class EvaluatorDisplayServlet extends HttpServlet {

    public EvaluatorDisplayServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        EvaluatorDisplayService evaluatorDisplayService;
        try {

            evaluatorDisplayService = new EvaluatorDisplayService(new UtenteDao());
            request.setAttribute("evaluatorsOld", evaluatorDisplayService.getValutatori());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("evaluators_display.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EvaluatorDisplayService evaluatorDisplayService;

        try {
            evaluatorDisplayService = new EvaluatorDisplayService(new UtenteDao());
            evaluatorDisplayService.removeFromEvaluator(Integer.parseInt(request.getParameter("evaluatorID")), request.getParameter("mansione"));
            request.setAttribute("evaluatorsNew", evaluatorDisplayService.getValutatori());
            request.setAttribute("waitingUsers", evaluatorDisplayService.getWaitingUsers());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("evaluators_display.jsp").forward(request, response);
    }


}