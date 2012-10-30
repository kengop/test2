package test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginCheck extends HttpServlet {

    protected Connection conn = null;

    public void init() throws ServletException{
	          String url = "jdbc:mysql://localhost/refrigerator";
	          String user = "root";
	          String password = "mindstorm";

	          try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            conn = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e){
            log("ClassNotFoundException:" + e.getMessage());
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }catch (Exception e){
            log("Exception:" + e.getMessage());
        }
    }

    public void destory(){
        try{
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        HttpSession session = request.getSession(true);

        boolean check = authUser(user, pass, session);
        if (check){
            /* �F�؍ς݂ɃZ�b�g */
            session.setAttribute("login", "OK");

            /* �{���̃A�N�Z�X��֔�΂� */
            String target = (String)session.getAttribute("target");
            response.sendRedirect(target);
        }else{
            /* �F�؂Ɏ��s������A���O�C����ʂɖ߂� */
            session.setAttribute("status", "Not Auth");
            response.sendRedirect("/refrigerator/LoginPage");
        }
    }

    protected boolean authUser(String user, String pass, HttpSession session){
        if (user == null || user.length() == 0 || pass == null || pass.length() == 0){
            return false;
        }
        System.out.println("���[�U�F�؊J�n");

        try {
            String sql = "SELECT * FROM usertable WHERE name = ? && password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("SQL����������");

            if (rs.next()){
                int userid = rs.getInt("id");
                String username = rs.getString("name");
                System.out.println("id:"+ userid + "   name:"+username);

                session.setAttribute("userid", Integer.toString(userid));
                session.setAttribute("username", username);

                System.out.println("���[�U�F�ؐ���");

                return true;
            }else{
            	System.out.println("���[�U�F�؎��s");
                return false;
            }
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
            return false;
        }
    }
}