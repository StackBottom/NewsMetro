package test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import pojo.HtmlBlock;

import util.HttpGetter;

public class UtilTest {

	/**
	 * @
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HttpGetter httpGetter = new HttpGetter();
//		Element rss = null;
//		try {
//			Document doc = httpGetter.toDom4jDoc(httpGetter.getDocument("http://timethief.me/rss"));
//			rss = doc.getRootElement();
//			httpGetter.getImageIcon(rss.element("channel").element("image").element("url").getData().toString());
//			
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String str = "<p><strong>���գ�ɳ�н�����Ϸ<em>Minecraft</em>������Mojang���������ǽ������ܷ������µڶ��������Ϸ--<em>Scrolls</em>��</strong>��Ϥ������һ�����ֽ��׿�Ƭ��Ϸ���˴η��е�����beta�档��<em>Polygon</em>�������������<em>Scrolls</em>��ɰ濪�����֮ǰ�Ͳ����˸ÿ���Ϸ�������ң�������ڽ�����ʽ���۵�ʱ����һ�����ۿ۹�����Ϸ��</p><p>&nbsp;</p><!--more--><p><span class=\"text-img-holder\"><img src=\"http://m2.img.libdd.com/farm5/2013/0327/09/0A735F531FD0CBF883AC6F8DD19D49CCD838DFA852F6A_400_225.jpg\" width=\"400\" height=\"225\"/></span></p><p><br /><em>Scrolls</em>�ڷ����ڼ���ۼ۽���<em>Minecraft</em>��࣬��13��Ԫ��26.95��Ԫ֮�䡣<br /><br />������Mojang����������beta��<em>Scrolls</em>��ֻ֧��PC��ң�Mac��iOS��Android��һ���Ҫ����һ��ʱ���������Ӧ��ƽ̨�Ͽ��������Ϸ��<br /><br />�ݽ��ܣ���ҽ�����Ϸ�н��׿�Ƭ������װ岢�Ҳμ������������ղؿ�Ƭ��Ϸ�ı�����<br /><br />��ȻMojang��ȫ����������Ϸ��Ӧ�����շѻ�ø�������󣬵�����ҿ������ƺ�����������ô����<br /><br />Mojang��һλ������Ա����<em>Polygon</em>����Ȼ��˾�������������Ϸ�ܹ���ø�<em>Minecraft</em>�����ĳɾͣ��������Ƿ�������Ƕ������зǳ�Ũ�ҵ���Ȥ��</p>";
		HtmlBlock hb = new HtmlBlock(str);
	}

}
