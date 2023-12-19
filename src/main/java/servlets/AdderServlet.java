package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "AdderServlet",
        urlPatterns = {"/add2"}
)
public class AdderServlet extends HttpServlet {
    public AdderServlet() {
        super();
    }

/*
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strNum1 = req.getParameter("num1");
        String strNum2 = req.getParameter("num2");
        if (strNum1 != null && strNum2 != null) {
            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);
            int sum = num1 + num2;

            PrintWriter out = resp.getWriter();
            out.println("The sum is: " + sum);
        }

    }

 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strNum1 = req.getParameter("num1");
        String strNum2 = req.getParameter("num2");

        int k = 0;
        if (strNum1 != null && strNum2 != null) {
            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);
            k = num1 + num2;
            req.getSession().setAttribute("k", k);
        }

        //resp.sendRedirect("add2.jsp");
        resp.sendRedirect("sq?k=" + k);
    }
}