package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "SqServlet",
        urlPatterns = {"/sq"}
)
public class SqServlet extends HttpServlet {
    public SqServlet() {
        super();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int k = Integer.parseInt(req.getParameter("k"));
        int sq = k * k;
        req.getSession().setAttribute("sq", sq);

        resp.sendRedirect("sq.jsp");
    }

}
