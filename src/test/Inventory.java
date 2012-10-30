package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Inventory extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

            response.setContentType("text/html; charset=Shift_JIS");
            PrintWriter w = response.getWriter();

            Display d = new Display(w);
            d.header("インベントリ");

            HttpSession session = request.getSession(false);
            String userid = (String) session.getAttribute("userid");

            w.println("<div class=\"container\">");
            w.println("<h1>冷蔵庫の中身</h1>");

            w.println("<p><a href=\"/refrigerator/logout\">ログアウト</a></p>");
            (new View(w, userid)).execute();
            w.println("</body>");
            w.println("</html>");
        }

}
