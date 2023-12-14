package servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.UtenteBean;
import model.dao.UtenteDao;

@WebServlet(
        name = "DistributeEvaluatorsServlet",
        urlPatterns = {"/UtentiGiovani"}
)
public class UtenteGiovaneServlet extends HttpServlet {

    public UtenteGiovaneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UtenteDao utenteDao;
		try {
			utenteDao = new UtenteDao();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        List<UtenteBean> dueUtentiPiuGiovani = utenteDao.getUtentiOrdinatiPerEta();

    
        List<UtenteBean> utentiInSospeso = utenteDao.getUtentiOrdinatiPerEta();

    
        request.setAttribute("dueUtentiPiuGiovani", dueUtentiPiuGiovani);
        request.setAttribute("utentiInSospeso", utentiInSospeso);

 
        request.getRequestDispatcher("/UtentiGiovani.jsp").forward(request, response);
    }
}
