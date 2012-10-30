package test;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SessionTest extends HttpServlet {
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException{

          response.setContentType("text/html; charset=Shift_JIS");
          PrintWriter out = response.getWriter();

          Display d = new Display(out);
          d.header("セッション関連テストページ");

	    HttpSession session = request.getSession(false);

        out.println("<div class=\"container\">");
        out.println("<h1>セッション関連テストページ</h1>");
        out.println("<div class=\"well\">");
        out.println("セッション関連の処理をテストするページ．完成時には消しましょう．");
        out.println("</div>");

	    if (session != null){
//	      session.removeAttribute("age");

	      /* 現在登録されているセッションオブジェクトを表示 */
	      out.println("<p>");
	      out.println("登録されているセッションオブジェクトを表示します<br>");

	      Enumeration enum_session = session.getAttributeNames();
	      while(enum_session.hasMoreElements()) {
	        String key = (String)enum_session.nextElement();
	        String val = (String)session.getAttribute(key);

	        out.println(key + " = " + val + "<br>");
	      }

	      out.println("</p>");
	    }else{
	      out.println("<p>セッションがありません</p>");
	    }

	    out.println("</body>");
	    out.println("</html>");
	  }
	}
