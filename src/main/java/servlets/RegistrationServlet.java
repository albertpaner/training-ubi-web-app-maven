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


    /**
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
            UtenteService utenteRegisterService = new UtenteService(new UtenteDao(DBConnection.createConnection()));
            if (utenteRegisterService.registrazioneUtente(email, password, ruoloId, nome, cognome, valutatoreId, dataNascita)>0) {
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