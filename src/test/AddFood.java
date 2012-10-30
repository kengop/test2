package test;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddFood extends Database {

	String name;
	String cord;
	ArrayList<Data> data;

	public AddFood(PrintWriter w, ArrayList<Data> idata) {
		super(w);
		// TODO 自動生成されたコンストラクター・スタブ
		data = idata;
	}

	@Override
	public void shori(Connection conn, Statement stmt) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

		StringBuilder sb = new StringBuilder();

        String strf = "INSERT INTO refrigerator.fooddb (id, cord, name) VALUES ";

        sb.append(strf);

        for(int i=0; i<data.size(); i++){
        	sb.append("(NULL, '" + data.get(i).getid() + "', '"+ data.get(i).getname() +"')");
        	if(i<data.size()-1){
        		sb.append(", ");
        	}
        }

        String sql = new String(sb);

        int num = stmt.executeUpdate(sql);
        out.println("数:" + num + "<br />");
        out.println("食品成分(null, " + cord + "," + name +")追加しました");



	}

}