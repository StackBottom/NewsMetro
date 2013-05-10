package test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetWebPageTest {
	public static void main(String args[]){
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet("http://www.baidu.com/");
		HttpResponse response = null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(request);
			entity = response.getEntity();
			if(entity!=null){
				System.out.println();
				Document doc = Jsoup.parse(EntityUtils.toString(entity,"UTF-8"));
				System.out.println(doc.outerHtml());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
