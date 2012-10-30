package test;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

abstract class Database {

	PrintWriter out;

    protected Connection conn = null;

	public Database(PrintWriter w){
		// superで呼ばれた実装クラスで実装される．
		out = w;
	}

	public void execute(){
	          String url = "jdbc:mysql://localhost/refrigerator";
	          String user = "root";
	          String password = "mindstorm";

	          try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            System.out.println("ドライバのロードに成功しました<br>");

	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("データベース接続に成功しました<br>");

	            Statement stmt = conn.createStatement();
	            shori(conn, stmt);
	            stmt.close();
	          }catch (ClassNotFoundException e){
	            System.out.println("ClassNotFoundException:" + e.getMessage());
	          }catch (SQLException e){
	            System.out.println("SQLException:" + e.getMessage());
	          }catch (Exception e){
	            System.out.println("Exception:" + e.getMessage());
	          }finally{
	            try{
	              if (conn != null){
	                conn.close();
	                System.out.println("データベース切断に成功しました");
	              }else{
	            	System.out.println("コネクションがありません");
	              }
	            }catch (SQLException e){
	            	System.out.println("SQLException:" + e.getMessage());
	            }
	          }

	}

	public abstract void shori(Connection conn, Statement stmt) throws SQLException;


    /* 食材の減少 */
    /* 増減の記録 */
    /* 重さの履歴を取得 */
    /* 関連度の高い食材を検索する */

}
