package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Recommendation extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

            response.setContentType("text/html; charset=Shift_JIS");
            PrintWriter out = response.getWriter();

            Display d = new Display(out);
            d.header("���V�s��ĉ��");

            HttpSession session = request.getSession(false);
            String userid = (String) session.getAttribute("userid");

            out.println("<div class=\"container\">");
            out.println("<h1>���V�s��ĉ��</h1>");

            out.println("<form class=\"form-inline\" method=\"POST\" action=\"/refrigerator/LoginCheck\" name=\"loginform\">");

            out.println("  <legend>�J�����[�Ō���</legend>");
            out.println("  <div class=\"control-group\">");
            out.println("    <label class=\"control-label\" for=\"inputCalorie\">�J�����[��</label>");
            out.println("    <div class=\"controls\">");
            out.println("      <input type=\"number\" name=\"cal\" placeholder=\"Calorie\">");
            out.println("      <button type=\"submit\" class=\"btn\">����</button>");
            out.println("    </div>");
            out.println("  </div>");
            out.println("</form>");

            (new View(out, userid)).execute();

            out.println("</body>");
            out.println("</html>");
        }
}
