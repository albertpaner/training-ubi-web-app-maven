package servlets;

import exceptions.RegistrationFailedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UtenteDao;
import services.user.UtenteRegisterService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {"/register"}
)
public class RegistrationServlet extends HttpServlet {

    public RegistrationServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int ruoloId = Integer.parseInt(request.getParameter("ruoloId"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        int valutatoreId = Integer.parseInt(request.getParameter("valutatoreId"));
        String mansione = request.getParameter("mansione");
        String jobLevel = request.getParameter("jobLevel");
        String societàOperativa = request.getParameter("societàOperativa");
        Date dataNascita = Date.valueOf(request.getParameter("dataNascita"));


        HttpSession session = request.getSession();
        try {
            UtenteRegisterService utenteRegisterService = new UtenteRegisterService(new UtenteDao());

            if (utenteRegisterService.registrazioneUtente(email, password, ruoloId, nome, cognome, valutatoreId, mansione, jobLevel, societàOperativa, dataNascita)>0) {
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