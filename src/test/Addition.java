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
            d.header("�H�i�ǉ����");

            out.println("<div class=\"container\">");
            out.println("<h1>�H�i�ǉ����</h1>");
            out.println("<p>�V�X�e���𗘗p���邽�߂ɂ̓��O�C�����Ē����K�v������܂��B���[�U�[���ƃp�X���[�h����͂��ă��O�C�����ĉ������B</p>");

            /* �Z�b�V�����̊J�n */
            HttpSession session = request.getSession(true);

            /* �F�؎��s����Ăяo���ꂽ�̂��ǂ������`�F�b�N */
            /* �Z�b�V������"status"���擾���� */
            Object status = session.getAttribute("status");

            if (status != null){
                out.println("<div class=\"alert alert-error\">");
//                out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
                out.println("<h4>�F�؂Ɏ��s���܂���</h4>���[�U�[���ƃp�X���[�h��������x���͂��ĉ�����");
                out.println("</div>");
                // "status"��null��ݒ�
                session.setAttribute("status", null);
            }

            out.println("  <legend>�蓮�ŐH�ޒǉ�</legend>");
            out.println("  <div class=\"control-group\">");
            out.println("    <div class=\"controls\">");
            out.println("      <button class=\"add_bottom\">�ǉ�</button>");
            out.println("    </div>");
            out.println("  </div>");



            out.println("<table class=\"table table-hover table-condensed\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th id=\"product_name\">�H�ޖ�</th>");
            out.println("<th id=\"product_price\">�W������</th>");
            out.println("<th id=\"product_number\">����</th>");
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
//		// TODO �����������ꂽ���\�b�h�E�X�^�u
//        String sql = "INSERT INTO refrigerator.category (id, name) VALUES (NULL, '"
//        + name + "')";
//        int num = stmt.executeUpdate(sql);
//        out.println("��:" + num + "<br />");
//        out.println("�J�e�S��(null, " + name +")�ǉ����܂���");
//
//	}

}
