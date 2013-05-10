package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static String driver=null;
	private static String url=null;

	private static Connection conn=null;
	
	//���ò���
	public static void setConnection(String driverParam,String urlParam){
	    driver=driverParam;
	    url=urlParam;
	}
	
	//������
	public static Connection getConnection(){
	    try {
		Class.forName(driver);
		conn=DriverManager.getConnection(url);
		conn.setAutoCommit(false);
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return conn;
	}
	
	
	//�ر�����
	public static void connClose(){
	    try {
		if(conn!=null && !conn.isClosed()){
		    conn.close();
		}
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
}
