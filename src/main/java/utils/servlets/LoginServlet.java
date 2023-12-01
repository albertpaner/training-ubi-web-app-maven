package utils.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import model.Bean.UtenteBean;
//import model.Dao.UtenteDao;
import services.UtenteService;




@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1 ;

   /*  private UtenteDao utenteDao;

    public void init() {
        utenteDao = new UtenteDao();
    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //UtenteBean utenteBean = new UtenteBean();
        UtenteService utenteService = new UtenteService();
        //utenteBean.setEmail(email);
        //utenteBean.setPassword(password);

        //String resultUtente = utenteService.loginUtente(email,password);

        try {

            String resultUtente = utenteService.loginUtente(email,password);

            if (!resultUtente.equals("try again")) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
                response.sendRedirect("loginsuccess.jsp");
            } 
            
            else {

                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
            }

        } 
        
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}