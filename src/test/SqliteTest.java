package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteTest {
	public static void main(String args[]){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:E:\\Sqlite\\NewsMetroDB.db");
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from T_Position where _id=2");
			System.out.println(rs.getString("_url"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
