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
		
		String str = "<p><strong>近日，沙盒建造游戏<em>Minecraft</em>开发商Mojang宣布，他们将在下周发布旗下第二款电脑游戏--<em>Scrolls</em>。</strong>据悉，这是一款数字交易卡片游戏，此次发行的则是beta版。据<em>Polygon</em>报道，如果是在<em>Scrolls</em>完成版开发完成之前就参与了该款游戏试玩的玩家，则可以在将来正式发售的时候以一定的折扣购买游戏。</p><p>&nbsp;</p><!--more--><p><span class=\"text-img-holder\"><img src=\"http://m2.img.libdd.com/farm5/2013/0327/09/0A735F531FD0CBF883AC6F8DD19D49CCD838DFA852F6A_400_225.jpg\" width=\"400\" height=\"225\"/></span></p><p><br /><em>Scrolls</em>在发布期间的售价将跟<em>Minecraft</em>差不多，从13美元到26.95美元之间。<br /><br />不过，Mojang即将发布的beta版<em>Scrolls</em>将只支持PC玩家，Mac、iOS及Android玩家还需要等上一段时间才能在相应的平台上看到这款游戏。<br /><br />据介绍，玩家将在游戏中交易卡片、建造甲板并且参加类似于物理收藏卡片游戏的比赛。<br /><br />虽然Mojang完全可以利用游戏的应用内收费获得更多的利润，但是这家开发商似乎并不打算这么做。<br /><br />Mojang的一位开发人员告诉<em>Polygon</em>，虽然公司并不期望这款游戏能够获得跟<em>Minecraft</em>那样的成就，但是他们发现玩家们对它抱有非常浓烈的兴趣。</p>";
		HtmlBlock hb = new HtmlBlock(str);
	}

}
