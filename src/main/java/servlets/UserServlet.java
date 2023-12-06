import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;

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

        UtenteDao utenteDao = new UtenteDao();

        List<UtenteBean> utenti = utenteDao.findAll();

        for (UtenteBean utente : utenti) {
            users.add(utente.getEmail());
        }
        																						//TEST//gg
        request.setAttribute("users", utenti);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listUser.jsp");
        dispatcher.forward(request, response);
    }
}