package test;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        session.invalidate();

        response.sendRedirect("/refrigerator/home");
    }
}
