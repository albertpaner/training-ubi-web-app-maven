package servlets;

import exceptions.RegistrationFailedException;
import model.Dao.UtenteDao;
import services.UtenteService;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {"/register"}
)
public class RegistrationServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int ruoloId = Integer.parseInt(request.getParameter("ruoloId"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        int valutatoreId = Integer.parseInt(request.getParameter("valutatoreId"));
        /*
        String societaOp = request.getParameter("societaOp");
        String mansione = request.getParameter("mansione");
        String ambito = request.getParameter("ambito");
        String jobFam = request.getParameter("jobFam");
        String subFam = request.getParameter("subFam");
        String stdJob = request.getParameter("stdJob");
        String jobLevel = request.getParameter("jobLevel");*/
        Date dataNascita = Date.valueOf(request.getParameter("dataNascita"));


        HttpSession session = request.getSession();
        try {
            UtenteService utenteRegisterService = new UtenteService(new UtenteDao(DBConnection.createConnection()));
            if (utenteRegisterService.registrazioneUtente(email, password, ruoloId, nome, cognome, valutatoreId, dataNascita)) {
                session.setAttribute("successMsg", "Registered Successfully");
                response.sendRedirect("register_success.jsp");
            } else {
                session.setAttribute("errorMsg", "Registration Failed");
                response.sendRedirect("register.jsp");
            }
        } catch (ClassNotFoundException | SQLException | RegistrationFailedException e) {
            throw new RuntimeException(e);
        }
    }
}