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
		// super�ŌĂ΂ꂽ�����N���X�Ŏ��������D
		out = w;
	}

	public void execute(){
	          String url = "jdbc:mysql://localhost/refrigerator";
	          String user = "root";
	          String password = "mindstorm";

	          try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            System.out.println("�h���C�o�̃��[�h�ɐ������܂���<br>");

	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("�f�[�^�x�[�X�ڑ��ɐ������܂���<br>");

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
	                System.out.println("�f�[�^�x�[�X�ؒf�ɐ������܂���");
	              }else{
	            	System.out.println("�R�l�N�V����������܂���");
	              }
	            }catch (SQLException e){
	            	System.out.println("SQLException:" + e.getMessage());
	            }
	          }

	}

	public abstract void shori(Connection conn, Statement stmt) throws SQLException;


    /* �H�ނ̌��� */
    /* �����̋L�^ */
    /* �d���̗������擾 */
    /* �֘A�x�̍����H�ނ��������� */

}
