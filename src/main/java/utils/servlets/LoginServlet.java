package utils.servlets;

import exceptions.LoginPasswordFailedException;
import exceptions.LoginUserNotFoundException;
import model.dao.UtenteDao;
import services.user.UtenteLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP POST method for user login.
     * This method retrieves the email and password parameters from the request,
     * and attempts to log in the user using these credentials. If the login is successful,
     * a JWT token is returned and stored in the session, and the user is redirected to the
     * 'loginsuccess.jsp' page. If the login fails, an error message is set in the request
     * and the user is forwarded to the 'login.jsp' page.
     *
     * @param request  the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the POST request
     * @see services.user.UtenteLogin#loginUtente(String, String)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UtenteLogin utenteLoginService = new UtenteLogin(new UtenteDao());
            String jwt = utenteLoginService.loginUtente(email, password);

            HttpSession session = request.getSession();
            session.setAttribute("jwt", jwt);
            response.sendRedirect("loginsuccess.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (LoginPasswordFailedException | LoginUserNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

}