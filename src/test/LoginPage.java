package test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginPage extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

        Display d = new Display(out);
        d.header("ログインページ");

        out.println("<div class=\"container\">");
        out.println("<h1>Login Page</h1>");
        out.println("<p>システムを利用するためにはログインして頂く必要があります。ユーザー名とパスワードを入力してログインして下さい。</p>");

        /* セッションの開始 */
        HttpSession session = request.getSession(true);

        /* 認証失敗から呼び出されたのかどうかをチェック */
        /* セッションの"status"を取得する */
        Object status = session.getAttribute("status");

        if (status != null){
            out.println("<div class=\"alert alert-error\">");
//            out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
            out.println("<h4>認証に失敗しました</h4>ユーザー名とパスワードをもう一度入力して下さい");
            out.println("</div>");
            // "status"にnullを設定
            session.setAttribute("status", null);
        }

        out.println("<form class=\"form-horizontal\" method=\"POST\" action=\"/refrigerator/LoginCheck\" name=\"loginform\">");

        out.println("  <legend>Login</legend>");
        out.println("  <div class=\"control-group\">");
        out.println("    <label class=\"control-label\" for=\"inputUsername\">Username</label>");
        out.println("    <div class=\"controls\">");
        out.println("      <input type=\"text\" name=\"user\" placeholder=\"Username\">");
        out.println("    </div>");
        out.println("  </div>");

        out.println("  <div class=\"control-group\">");
        out.println("    <label class=\"control-label\" for=\"inputPassword\">Password</label>");
        out.println("    <div class=\"controls\">");
        out.println("      <input type=\"password\" name=\"pass\" placeholder=\"Password\">");
        out.println("    </div>");
        out.println("  </div>");


        out.println("  <div class=\"control-group\">");
        out.println("    <div class=\"controls\">");
        out.println("      <button type=\"submit\" class=\"btn\">Sign in</button>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</form>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}