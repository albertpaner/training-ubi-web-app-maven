import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.CountDto;
import services.UtenteService;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> users = new ArrayList<String>();
        Connection conn;
        Statement stmt;
        ResultSet rs;

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
//new servlet
        UtenteDao utenteDao = new UtenteDao();
        UtenteService utenteService = new UtenteService(utenteDao);
        HashMap<String, List<CountDto>> evaluators = utenteService.getEvaluators();

        List<CountDto> evaluatorsOccupied = evaluators.get("valutatori_occupati");
        List<CountDto> evaluatorsFree = evaluators.get("valutatori_disponibili");

        request.setAttribute("valutatori_occupati", evaluatorsOccupied);
        request.setAttribute("valutatori_disponibili", evaluatorsFree);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listUser.jsp");
        dispatcher.forward(request, response);
    }
}