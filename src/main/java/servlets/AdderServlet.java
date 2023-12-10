package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "AdderServlet",
        urlPatterns = {"/add2"}
)
public class AdderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strNum1 = req.getParameter("num1");
        String strNum2 = req.getParameter("num2");
        if (strNum1 != null && strNum2 != null) {
            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);
            int sum = num1 + num2;
            req.getSession().setAttribute("sum", sum);
        }
        resp.sendRedirect("add2.jsp");
    }
}