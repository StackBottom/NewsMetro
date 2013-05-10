package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    private String[] columns=null;

    public BaseDao() {
	this.conn = DBConnection.getConnection();
    }

    /*
     * ���ؽ������������
     */
    public List<Map> query(String sql) throws SQLException {
	List<Map> list = new ArrayList<Map>();
	HashMap map = null;
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	this.setColumns();
	int columnsLength=this.columns.length;
	while (rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	    list.add(map);
	}
	rs.close();
	stmt.close();
	return list;
    }

    /*
     * ���ؽ������������
     */
    public List<Map> query(String sql, Object args[]) throws SQLException {
	ArrayList<Map> list = new ArrayList<Map>();
	Map map = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	int columnsLength=this.columns.length;
	while (rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	    list.add(map);  
	}
	rs.close();
	pstmt.close();
	return list;
    }

    /*
     *  ���ص�һ����
     */
    public Map findByConditions(String sql, Object[] args) throws SQLException {
	Map map = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	int columnsLength=this.columns.length;
	if(rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	}
	rs.close();
	pstmt.close();
	return map;
    }

    /*
     * ����ͳ��ֵ��������
     */
    public Object findByFunction(String sql) throws SQLException {
	Object result = null;
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	this.setColumns();
	if (rs.next()) {
	    result = rs.getObject(1);
	}
	rs.close();
	stmt.close();
	return result;
    }

    /*
     * ����ͳ��ֵ��������
     */
    public Object findByFunction(String sql, Object[] args) throws SQLException {
	Object result = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	if (rs.next()) {
	    result = rs.getObject(1);
	}
	rs.close();
	pstmt.close();
	return result;
    }

    /*
     *  ��ɾ�ģ�������
     */
    public int update(String sql) throws SQLException {
	int flag = 0;
	stmt = conn.createStatement();
	flag = stmt.executeUpdate(sql);
	stmt.close();
	return flag;
    }

    /*
     *  ��ɾ�ģ�������
     */
    public int update(String sql, Object[] args) throws SQLException {
	int flag = 0;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	flag = pstmt.executeUpdate();
	pstmt.close();
	return flag;
    }

    /*
     *  ������ɾ��,������
     */
    public int[] updateAll(String[] sql) throws SQLException {
	int[] flags = null;
	stmt = conn.createStatement();
	for (int i = 0; i < sql.length; i++) {
	    stmt.addBatch(sql[i]);
	}
	flags = stmt.executeBatch();
	stmt.close();
	return flags;
    }

    /*
     *  ������ɾ��,������
     */
    public int[] updateAll(String[] sql, List<Object[]> args) throws SQLException {
	int[] flags = new int[sql.length];
	for (int i = 0; i < args.size(); i++) {
    	    pstmt = conn.prepareStatement(sql[i]);
    	    Object[] arg = args.get(i);
    	    for (int j = 0; j < arg.length; j++) {
    	        pstmt.setObject(j + 1, arg[j]);
    	    }
    	    flags[i] = pstmt.executeUpdate();    
    	    pstmt.close();
	}
	return flags;
    }

    /*
     *  һ����ɾ��ִ�ж��
     */
    public int[] updateMore(String sql, List<Object[]> args) throws SQLException {
	int[] flags = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.size(); i++) {
	    Object[] arg = args.get(i);
	    for (int j = 0; j < arg.length; j++) {
		pstmt.setObject(j + 1, arg[j]);
	    }
	    pstmt.addBatch();
	}
	flags = pstmt.executeBatch();
	pstmt.close();
	return flags;
    }

    /*
     * 	������������
     */
    private void setColumns() throws SQLException {
	String[] colunms = null;
	if (rs != null) {
	    ResultSetMetaData rsmd = rs.getMetaData();
	    colunms = new String[rsmd.getColumnCount()];
	    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		colunms[i - 1] = rsmd.getColumnName(i);
	    }
	}
	this.columns=colunms;
    }
    
    /*
     * ������������
     */
    
    public String[] getColumns(){
	return this.columns;
    }
    
    /*
     *  �����ύ
     */
    public void commit() throws SQLException {
	conn.commit();
    }

    /*
     *  ����һ�������
     */
    public Savepoint savepoint(String name) throws SQLException {
	Savepoint savepoint = null;
	savepoint = conn.setSavepoint(name);
	return savepoint;
    }

    /*
     *  ���ݻع�
     */
    public void rollBack() throws SQLException {
	conn.rollback();
    }

    /*
     *  ���ݻع���һ�������
     */
    public void rollBack(Savepoint savepoint) throws SQLException {
	conn.rollback(savepoint);
    }
    /*
     *  �ر�����
     */
    public void closeConnection() throws SQLException{
    	this.conn.close();
    }
}
