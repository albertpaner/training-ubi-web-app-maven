package servlets;

import exceptions.RegistrationFailedException;
import services.UtenteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1 ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int ruoloId = Integer.parseInt(request.getParameter("ruoloId"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        int valutatoreId = Integer.parseInt(request.getParameter("valutatoreId"));
        String societaOp = request.getParameter("societaOp");
        String mansione = request.getParameter("mansione");
        String ambito = request.getParameter("ambito");
        String jobFam = request.getParameter("jobFam");
        String subFam = request.getParameter("subFam");
        String stdJob = request.getParameter("stdJob");
        String jobLevel = request.getParameter("jobLevel");

        UtenteService utenteService = new UtenteService();

        try {
            if (utenteService.registrazioneUtente(email, password, ruoloId, nome, cognome, valutatoreId, societaOp, mansione, ambito, jobFam, subFam, stdJob, jobLevel) != -1) {
                response.sendRedirect("registerSuccess.jsp");
            } else {
                response.sendRedirect("register.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RegistrationFailedException e) {
            throw new RuntimeException(e);
        }
    }
}