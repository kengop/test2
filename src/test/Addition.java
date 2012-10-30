package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Addition extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

            response.setContentType("text/html; charset=Shift_JIS");
            PrintWriter out = response.getWriter();

            Display d = new Display(out);
            d.header("食品追加画面");

            out.println("<div class=\"container\">");
            out.println("<h1>食品追加画面</h1>");
            out.println("<p>システムを利用するためにはログインして頂く必要があります。ユーザー名とパスワードを入力してログインして下さい。</p>");

            /* セッションの開始 */
            HttpSession session = request.getSession(true);

            /* 認証失敗から呼び出されたのかどうかをチェック */
            /* セッションの"status"を取得する */
            Object status = session.getAttribute("status");

            if (status != null){
                out.println("<div class=\"alert alert-error\">");
//                out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
                out.println("<h4>認証に失敗しました</h4>ユーザー名とパスワードをもう一度入力して下さい");
                out.println("</div>");
                // "status"にnullを設定
                session.setAttribute("status", null);
            }

            out.println("  <legend>手動で食材追加</legend>");
            out.println("  <div class=\"control-group\">");
            out.println("    <div class=\"controls\">");
            out.println("      <button class=\"add_bottom\">追加</button>");
            out.println("    </div>");
            out.println("  </div>");



            out.println("<table class=\"table table-hover table-condensed\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th id=\"product_name\">食材名</th>");
            out.println("<th id=\"product_price\">ジャンル</th>");
            out.println("<th id=\"product_number\">数量</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            	out.println("<tr>");


              out.println("<td>" + "</td>");
              out.println("<td>" + "</td>");
              out.println("<td>" + "</td>");
              out.println("</tr>");


            out.println("</tbody>");
            out.println("</table>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }


//	@Override
//	public void shori(Connection conn, Statement stmt) throws SQLException {
//		// TODO 自動生成されたメソッド・スタブ
//        String sql = "INSERT INTO refrigerator.category (id, name) VALUES (NULL, '"
//        + name + "')";
//        int num = stmt.executeUpdate(sql);
//        out.println("数:" + num + "<br />");
//        out.println("カテゴリ(null, " + name +")追加しました");
//
//	}

}
