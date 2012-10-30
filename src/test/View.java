package test;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 冷蔵庫の中身取得 */
public class View extends Database {

	String userid;

	public View(PrintWriter w, String userid_i) {
		super(w);
		// TODO 自動生成されたコンストラクター・スタブ
		userid = userid_i;
	}

	@Override
	public void shori(Connection conn, Statement stmt) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
        String sql = "SELECT category.name, COUNT(cat_id) AS num FROM inventory JOIN category ON inventory.cat_id = category.id WHERE user_id = " + userid + " GROUP BY cat_id";
        ResultSet rs = stmt.executeQuery(sql);

        out.println("<table class=\"table table-hover table-condensed\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>食材</th>");
        out.println("<th>数量</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        while(rs.next()){
        	out.println("<tr>");
          String code = rs.getString("category.name");
          String num = rs.getString("num");


//          String sqlcat = "SELECT * FROM category WHERE id =" + code;
//          ResultSet rscat = stmt.executeQuery(sqlcat);
//          System.out.println("a");
//          String catname = rscat.getString("name");
//          System.out.println("b");
//          rscat.close();

          out.println("<td>" + code + "</td>");
          out.println("<td>" + num + "</td>");
//          out.println("コード:" + code + ", ジャンル名:" + name);
          out.println("</tr>");

        }
        out.println("</tbody>");
        out.println("</table>");

        rs.close();
	}

}
