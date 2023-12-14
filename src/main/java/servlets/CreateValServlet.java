package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UtenteDao;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(name = "CreateValServlet", urlPatterns = { "/visualizza_val" })

public class CreateValServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateValServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        Timestamp dataUltMod = Timestamp.valueOf(request.getParameter("data_ult_mod"));
        Timestamp dataCreaz = Timestamp.valueOf(request.getParameter("data_creaz"));
        Timestamp dataUltAcc = Timestamp.valueOf(request.getParameter("data_ult_acc"));

        UtenteDao utenteDao = null;
		try {
			utenteDao = new UtenteDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean success = utenteDao.createVal(email, password, nome, cognome, dataUltMod, dataCreaz, dataUltAcc);

        if (success) {
            request.setAttribute("email", email);
            request.setAttribute("nome", nome);
            request.setAttribute("cognome", cognome);
            request.setAttribute("data_ult_mod", dataUltMod);
            request.setAttribute("data_creaz", dataCreaz); 
            request.setAttribute("data_ult_acc", dataUltAcc);

            request.getRequestDispatcher("nuovoValutatore.jsp").forward(request, response);
        } else {
            response.getWriter().println("Errore nella creazione del nuovo valutatore.");
        }
    }
}

