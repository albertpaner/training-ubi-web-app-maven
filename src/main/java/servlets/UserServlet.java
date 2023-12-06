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

@WebServlet("/listUser")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String driver, url, username, password;

        try (InputStream input = new FileInputStream(new File("db.properties"))) {
            Properties prop = new Properties();
            prop.load(input);

            driver = prop.getProperty("DRIVER");
            url = prop.getProperty("URL");
            username = prop.getProperty("USERNAME");
            password = prop.getProperty("PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//new user servlet
        UtenteDao utenteDao = new UtenteDao();
        UtenteService utenteService = new UtenteService(utenteDao);
        HashMap<String, List<CountDto>> evaluators = new HashMap<>();
        try {
            evaluators = utenteService.getEvaluators();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<CountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
        List<CountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);
        /* 
        RequestDispatcher dispatcher = request.getRequestDispatcher("listUser.jsp");
        dispatcher.forward(request, response); */
        response.sendRedirect("listUser.jsp");
    }
}