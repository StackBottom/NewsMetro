package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Position;

public class BaseDao {
	private Connection conn = null;
	public BaseDao(){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:E:\\Sqlite\\NewsMetroDB.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Position> executeQuery(String sql){
		ResultSet rs = null;
		List<Position> list = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			list = new ArrayList<Position>();
			while(rs.next()){
				Position pos = new Position();
				pos.setId(rs.getInt("_id"));
				pos.setRss(rs.getBoolean("_isRss"));
				pos.setUrl(rs.getString("_url"));
				pos.setPath(rs.getString("_path"));
				pos.setName(rs.getString("_name"));
				pos.setLastHref(rs.getString("_lastHref"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Position> queryAllPosition(){
		return executeQuery("select * from T_Position");
	}
	
	public List<Position> queryRssPosition(){
		return executeQuery("select * from T_Position where _isRss='true'");
	}
	
	public Position getPositionById(Integer id){
		return executeQuery("select * from T_Position where _id="+id.toString()).get(0);
	}
	public List<Position> queryPositionByUrl(String url){
		return executeQuery("select * from T_Position where _url="+url);
	}
	public boolean addPosition(Position pos){
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into T_Position value(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pos.getId());
			pstmt.setBoolean(2, pos.isRss());
			pstmt.setString(3, pos.getUrl());
			pstmt.setString(4, pos.getPath());
			pstmt.setString(5, pos.getContext());
			pstmt.setString(6, pos.getName());
			pstmt.setString(7, pos.getLastHref());
			flag = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean updatePosition(Position pos){
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update T_Position set _isRss=? _url=? _path=? _context=? _name=? _lastHref=? _tags=? where id="+pos.getId());
			pstmt.setBoolean(2, pos.isRss());
			pstmt.setString(3, pos.getUrl());
			pstmt.setString(4, pos.getPath());
			pstmt.setString(5, pos.getContext());
			pstmt.setString(6, pos.getName());
			pstmt.setString(7, pos.getLastHref());
			flag = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean deletePosition(int id){
		boolean flag = false;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.execute("delete from T_Position where id="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static void main(String args[]){
		BaseDao dao = new BaseDao();
		List<Position> list = dao.executeQuery("select * from T_Position");
		list.get(0).getName();
	}
}
