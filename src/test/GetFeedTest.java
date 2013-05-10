package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class GetFeedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet("http://hsenf.com/rss");
		HttpResponse response = null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(request);
			entity = response.getEntity();
			System.out.println("rssdoc.isStreaming():"+entity.isStreaming());
			System.out.println("rssdoc.getContentType():"+entity.getContentType());
			if(entity!=null){
				//System.out.println(EntityUtils.toString(entity,"UTF-8"));
				
				Document doc = DocumentHelper.parseText(EntityUtils.toString(entity,"UTF-8"));
				Element rss = doc.getRootElement();
				System.out.println(rss.element("channel").element("title").getData().toString());
				HttpUriRequest get = new HttpGet(rss.element("channel").element("image").element("url").getData().toString());
				HttpResponse res = httpClient.execute(get);
				System.out.println(res.getAllHeaders());
				File storeFile = new File("E:\\test.jpg");
				FileOutputStream output = new FileOutputStream(storeFile);
				InputStream is = res.getEntity().getContent();
				byte [] cache = new byte[10*1024];
				int i = 0;
				while((i=is.read(cache))!=-1){
					output.write(cache,0,i);
				}
				is.close();
				output.flush();
				output.close();
//				for ( Iterator i = root.elementIterator("item"); i.hasNext();) {
//				       Element item = (Element) i.next();
//				       
//				}
//				OutputFormat format = OutputFormat.createPrettyPrint();
//				XMLWriter writer = new XMLWriter(new FileOutputStream("D:\\test.xml"), format );
//				document.setXMLEncoding("UTF-8");
//			    writer.write( document );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
