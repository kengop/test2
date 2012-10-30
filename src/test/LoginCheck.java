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
            /* 認証済みにセット */
            session.setAttribute("login", "OK");

            /* 本来のアクセス先へ飛ばす */
            String target = (String)session.getAttribute("target");
            response.sendRedirect(target);
        }else{
            /* 認証に失敗したら、ログイン画面に戻す */
            session.setAttribute("status", "Not Auth");
            response.sendRedirect("/refrigerator/LoginPage");
        }
    }

    protected boolean authUser(String user, String pass, HttpSession session){
        if (user == null || user.length() == 0 || pass == null || pass.length() == 0){
            return false;
        }
        System.out.println("ユーザ認証開始");

        try {
            String sql = "SELECT * FROM usertable WHERE name = ? && password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("SQL文準備完了");

            if (rs.next()){
                int userid = rs.getInt("id");
                String username = rs.getString("name");
                System.out.println("id:"+ userid + "   name:"+username);

                session.setAttribute("userid", Integer.toString(userid));
                session.setAttribute("username", username);

                System.out.println("ユーザ認証成功");

                return true;
            }else{
            	System.out.println("ユーザ認証失敗");
                return false;
            }
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
            return false;
        }
    }
}