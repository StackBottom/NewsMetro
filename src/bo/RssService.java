package bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import pojo.Position;
import pojo.Rss;
import pojo.RssItem;
import util.BaseDao;
import util.HttpGetter;
import util.RssDao;

public class RssService {
	private RssDao dao = null;
	private HttpGetter getter = null;
	public RssService(){
		this.dao = new RssDao();
		this.getter = new HttpGetter();
	}
	
	public List<Position> allRssPos(){
		List<Position> list = null;
		try {
			list = dao.queryRssPosition();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public Rss getRss(String url){
		Document doc = null;
		try {
			doc = getter.toDom4jDoc(getter.getDocument(url));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Rss(doc);
	}
	
	public boolean addPosition(Position pos){
		try {
			return dao.addPosition(pos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletePosition(int id){
		try {
			return dao.deletePosition(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String [] args){
		List<Position> list = new RssService().allRssPos();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}
	}
}
