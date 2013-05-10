package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pojo.Position;

public class RssDao {
	private static String driverParam = "org.sqlite.JDBC";
	private static String urlParam = "jdbc:sqlite:E:\\Sqlite\\NewsMetroDB.db";
	private static BaseDao dao = null;
	public RssDao(){
		DBConnection.setConnection(driverParam, urlParam);
		//Connection conn = DBConnection.getConnection();
		dao = new BaseDao();
	}
	
	public List<Position> allPosition() throws SQLException{
		String sql = "select * from T_Position";
		List<Position> list = new ArrayList<Position>();
		List<Map> rs = dao.query(sql);
		for(int i=0;i<rs.size();i++){
			Position pos = new Position();
			pos.setId((Integer)rs.get(i).get(0));
			pos.setRss(Boolean.parseBoolean(rs.get(i).get(1).toString()));
			pos.setUrl((String)rs.get(i).get(2));
			pos.setPath((String)rs.get(i).get(3));
			pos.setContext((String)rs.get(i).get(4));
			pos.setName((String)rs.get(i).get(5));
			pos.setLastHref((String)rs.get(i).get(6));
			pos.setTags(null);
			list.add(pos);
		}
		return list;
	}
	
	public List<Position> queryRssPosition() throws SQLException{
		String sql = "select * from T_Position where _isRss='true'";
		List<Position> list = new ArrayList<Position>();
		List<Map> rs = dao.query(sql);
		for(int i=0;i<rs.size();i++){
			Position pos = new Position();
			pos.setId((Integer)rs.get(i).get(0));
			pos.setRss(Boolean.parseBoolean(rs.get(i).get(1).toString()));
			pos.setUrl((String)rs.get(i).get(2));
			pos.setPath((String)rs.get(i).get(3));
			pos.setContext((String)rs.get(i).get(4));
			pos.setName((String)rs.get(i).get(5));
			pos.setLastHref((String)rs.get(i).get(6));
			pos.setTags(null);
			list.add(pos);
		}
		return list;
	}
	public boolean addPosition(Position pos) throws SQLException{
		String sql = "insert into T_Position value(?,?,?,?,?,?,?)";
		Object params[] = new Object[]{
				pos.isRss(),
				pos.getUrl(),
				pos.getPath(),
				pos.getContext(),
				pos.getName(),
				pos.getLastHref(),
				pos.getTags(),
				pos.getId()
				};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	public boolean updatePosition(Position pos) throws SQLException{
		String sql = "update T_Position set _isRss=? _url=? _path=? _context=? _name=? _lastHref=? _tags=? where id=?";
		Object params[] = new Object[]{
				pos.isRss(),
				pos.getUrl(),
				pos.getPath(),
				pos.getContext(),
				pos.getName(),
				pos.getLastHref(),
				pos.getTags(),
				pos.getId()
				};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean deletePosition(int id) throws SQLException{
		String sql = "delete from T_Position where id=?";
		Object params[] = new Object[]{id};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String args[]){
		try {
			List<Position> list = new RssDao().allPosition();
			System.out.println(list.get(1).getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
