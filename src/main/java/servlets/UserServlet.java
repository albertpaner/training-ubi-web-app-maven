package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.UtenteDao;
import model.Dto.CountDto;
import services.UtenteService;
import utils.DBConnection;

@WebServlet("/listuser")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // new user servlet
        HashMap<String, List<CountDto>> evaluators = new HashMap<>();

        try {
            evaluators = new UtenteService(new UtenteDao(DBConnection.createConnection())).getEvaluators();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        List<CountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
        List<CountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);
        /*
         * RequestDispatcher dispatcher = request.getRequestDispatcher("listUser.jsp");
         * dispatcher.forward(request, response);
         */
        response.sendRedirect("listuser.jsp");
    }
}