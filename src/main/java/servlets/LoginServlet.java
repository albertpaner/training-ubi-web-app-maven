package servlets;

import exceptions.LoginPasswordFailedException;
import exceptions.LoginUserNotFoundException;
import services.UtenteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteService utenteService = new UtenteService();
   
        try {

            String resultUtente = utenteService.loginUtente(email,password);

            if (!resultUtente.equals("try again")) {
                //HttpSession session = request.getSession();
                //session.setAttribute("username",username);
                response.sendRedirect("loginsuccess.jsp");
            } 
            
            else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
            }

        } 
        
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (LoginPasswordFailedException e) {
            throw new RuntimeException(e);
        } catch (LoginUserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
   
}