package servlets;

import exceptions.RegistrationFailedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.dao.UtenteDao;
import services.user.UtenteRegister;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {"/register"}
)
public class RegistrationServlet extends HttpServlet {

    public RegistrationServlet() {
        super();
    }

    /**
     *  Handles the HTTP POST method for user registration.
     *  This method is called when the user clicks on the "Register" button
     *  in the register.jsp page.
     *  It takes the parameters from the request and calls the registration method
     *  of the UtenteService class.
     *  If the registration is successful, the user is redirected to the register_success.jsp page.
     *  Otherwise, the user is redirected to the register.jsp page.
     *
     *  @param request the HttpServletRequest object containing the request parameters
     *                 (email, password, ruoloId, nome, cognome, valutatoreId, dataNascita)
     *  @param response the HttpServletResponse object containing the response
     *                  (successMsg or errorMsg)
     *  @throws ServletException
     *  @throws IOException
     *  @see services.user.UtenteRegister#registrazioneUtente(List) 
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int ruoloId = Integer.parseInt(request.getParameter("ruoloId"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        int valutatoreId = Integer.parseInt(request.getParameter("valutatoreId"));
        Date dataNascita = Date.valueOf(request.getParameter("dataNascita"));


        HttpSession session = request.getSession();
        try {
            UtenteRegister utenteRegisterService = new UtenteRegister(new UtenteDao());

            if (utenteRegisterService.registrazioneUtente(Arrays.asList(email, password, ruoloId, nome, cognome, valutatoreId, dataNascita))>0) {
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