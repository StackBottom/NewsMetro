package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jsoup.Jsoup;

import util.HttpGetter;

public class ShowImageTest extends JFrame {

	/**
	 * @param args
	 */
	String filename; 

    public ShowImageTest (String filename) { 
        this.setSize(420,350); 

        this.setVisible(true); 

        this.filename = filename; 
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);     //请把editorPane设置为只读，不然显示就不整齐
        HttpGetter getter = new HttpGetter();
  	  Document doc;
  	  try {
  		doc = getter.toDom4jDoc(getter.getDocument("http://techplanets.net/rss"));
  		List<Element> itemList = (List<Element>)doc.getRootElement().element("channel").elements("item");
  		editorPane.setContentType("text/html");
  		String body = Jsoup.parseBodyFragment(itemList.get(itemList.size()-1).element("description").getText()).html();
			editorPane.setText(body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Container c=getContentPane();
		JScrollPane scrollPane = new JScrollPane(editorPane);
		c.add(scrollPane,BorderLayout.CENTER);
    }

    public void paint (Graphics g) { 
    	 super.paint(g);
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShowImageTest("D:/logo.jpg").setVisible(true);
	}

}
