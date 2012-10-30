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
          d.header("�Z�b�V�����֘A�e�X�g�y�[�W");

	    HttpSession session = request.getSession(false);

        out.println("<div class=\"container\">");
        out.println("<h1>�Z�b�V�����֘A�e�X�g�y�[�W</h1>");
        out.println("<div class=\"well\">");
        out.println("�Z�b�V�����֘A�̏������e�X�g����y�[�W�D�������ɂ͏����܂��傤�D");
        out.println("</div>");

	    if (session != null){
//	      session.removeAttribute("age");

	      /* ���ݓo�^����Ă���Z�b�V�����I�u�W�F�N�g��\�� */
	      out.println("<p>");
	      out.println("�o�^����Ă���Z�b�V�����I�u�W�F�N�g��\�����܂�<br>");

	      Enumeration enum_session = session.getAttributeNames();
	      while(enum_session.hasMoreElements()) {
	        String key = (String)enum_session.nextElement();
	        String val = (String)session.getAttribute(key);

	        out.println(key + " = " + val + "<br>");
	      }

	      out.println("</p>");
	    }else{
	      out.println("<p>�Z�b�V����������܂���</p>");
	    }

	    out.println("</body>");
	    out.println("</html>");
	  }
	}
