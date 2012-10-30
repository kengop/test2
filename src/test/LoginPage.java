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
        d.header("���O�C���y�[�W");

        out.println("<div class=\"container\">");
        out.println("<h1>Login Page</h1>");
        out.println("<p>�V�X�e���𗘗p���邽�߂ɂ̓��O�C�����Ē����K�v������܂��B���[�U�[���ƃp�X���[�h����͂��ă��O�C�����ĉ������B</p>");

        /* �Z�b�V�����̊J�n */
        HttpSession session = request.getSession(true);

        /* �F�؎��s����Ăяo���ꂽ�̂��ǂ������`�F�b�N */
        /* �Z�b�V������"status"���擾���� */
        Object status = session.getAttribute("status");

        if (status != null){
            out.println("<div class=\"alert alert-error\">");
//            out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
            out.println("<h4>�F�؂Ɏ��s���܂���</h4>���[�U�[���ƃp�X���[�h��������x���͂��ĉ�����");
            out.println("</div>");
            // "status"��null��ݒ�
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