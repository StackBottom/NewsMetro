package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pojo.Position;
import pojo.Rss;
import pojo.RssItem;
import bo.RssService;

public class RssView extends JFrame{
	public static RssService rs = new RssService();
	public RssView () {
		super();
		ini();
		
	}
	public void ini(){
		this.setSize(1000,600);
		this.setVisible(true); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		this.add(new Container());
		this.setTitle("NewsMetro");
	}
	
	public class Container extends JPanel{
		public Container(){
			super();
			GridBagLayout gbl = new GridBagLayout();
			this.setLayout(gbl);
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridx=0;
	        gbc.gridy=0;
	        gbc.gridwidth=1;
	        gbc.gridheight=1;
	        gbc.weightx=0;
	        gbc.weighty=1;
	        gbc.anchor=GridBagConstraints.WEST;  
	        gbc.fill=GridBagConstraints.VERTICAL;
	        gbc.insets=new Insets(0,0,0,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
			this.add(new LeftMenu(),gbc);
			gbc.gridx=GridBagConstraints.RELATIVE;
	        gbc.gridy=0;
	        gbc.gridwidth=1;
	        gbc.gridheight=1;
	        gbc.weightx=1;
	        gbc.weighty=1;
	        gbc.anchor=GridBagConstraints.EAST;  
	        gbc.fill=GridBagConstraints.BOTH;
	        gbc.insets=new Insets(0,0,0,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
	        JLayeredPane layeredPane = new JLayeredPane();
	        MainPanel mainPanel = new MainPanel();
	        mainPanel.setBounds(new Rectangle(0,0,750,590));
	        NewsContent newsContent = new NewsContent(null);
	        newsContent.setBounds(new Rectangle(0,0,750,590));
	        layeredPane.add(mainPanel, new Integer(100));
	        layeredPane.add(newsContent,new Integer(10));
			this.add(layeredPane,gbc);
		}
		
	}
	
	public class LeftMenu extends JPanel{
		public LeftMenu(){
			super();
			this.setLayout(new FlowLayout());
			this.setPreferredSize(new Dimension(140,0));
			this.setMinimumSize(new Dimension(140,0));
			this.setMaximumSize(new Dimension(140,0));
			this.setBackground(new Color(255,255,255));
			List<Position> list = rs.allRssPos();
			for(int i=0;i<list.size();i++){
				Position pos = list.get(i);
				this.add(new LinkLabel(pos.getName(),pos.getUrl(),138));
			}
//			List<RssItem> itemList = rs.getRss(list.get(0).getUrl()).getItemList();
//			for(int i=itemList.size();i>=itemList.size()-7;i--){
//				System.out.println(itemList.get(i-1).getTitle());
//			}
			
		}
		
	}
	public class MainPanel extends JPanel{
		public MainPanel(){
			super(new GridLayout(1,2));
			List<Rss> rssList = new ArrayList<Rss>();
			for(Position pos:rs.allRssPos()){
				rssList.add(rs.getRss(pos.getUrl()));
			}
			NewsPanel left = new NewsPanel(rssList);
			this.add(left);
			NewsPanel right = new NewsPanel(rssList);
			this.add(right);
		}
	}
	public class NewsPanel extends JPanel{
		public NewsPanel(List<Rss> rssList){
			super(new FlowLayout());
			this.setPreferredSize(new Dimension(350,580));
			for(int i=0;i<rssList.size();i++){
				this.add(new NewsBlock(rssList.get(i)));
			}
		}
	}
	public class NewsBlock extends JPanel{
		public NewsBlock(Rss rss){
			super(new FlowLayout());
			this.setPreferredSize(new Dimension(340,6*30));
			List<RssItem> itemList = rss.getItemList();
			for(int i=itemList.size();i>itemList.size()-5;i--){
				NewsLabel item = new NewsLabel(itemList.get(i-1).getTitle(),itemList.get(i-1).getLinkUrl(),itemList.get(i-1),280);
				this.add(item);
			}
			
		}
	}
	public class NewsContent extends JPanel{
		private RssItem item = null;
		JScrollPane jsp = null;
		private JPanel left = null;
		private JPanel title = null;
		private GridBagConstraints gbc = null;
		private JPanel content = null;
		private JPanel right = null;
		public NewsContent(RssItem item){
			super(new GridBagLayout());
			this.item = item;
			paint();
		}
		public void paint(){
			jsp=new JScrollPane();
			jsp.setPreferredSize(new Dimension(640,590));
			left = new JPanel(new GridBagLayout());
			left.setPreferredSize(new Dimension(640,590));
			title = new JPanel();
			title.setPreferredSize(new Dimension(450,60));
			title.setFont(new Font("宋体",Font.BOLD,16));
			title.add(new JLabel(this.item!=null?item.getTitle():null));
			gbc = new GridBagConstraints();
			gbc.gridx=0;
	        gbc.gridy=0;
	        gbc.gridwidth=1;
	        gbc.gridheight=1;
	        gbc.weightx=0;
	        gbc.weighty=0;
	        gbc.anchor=GridBagConstraints.CENTER;  
	        gbc.fill=GridBagConstraints.HORIZONTAL;
	        gbc.insets=new Insets(0,0,50,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
			left.add(title,gbc);
			content = new JPanel();
			JTextArea textArea = new JTextArea(this.item!=null?item.getDescription().getDescreption():null);
			textArea.setFont(new Font("宋体",0,14));
			textArea.setBackground(new Color(238,238,238));
			textArea.setLineWrap(true);
			textArea.setPreferredSize(new Dimension(450,550));
			content.add(textArea);
			gbc.gridx=0;
	        gbc.gridy=GridBagConstraints.RELATIVE;
	        gbc.gridwidth=1;
	        gbc.gridheight=GridBagConstraints.REMAINDER;
	        gbc.weightx=0;
	        gbc.weighty=1;
	        gbc.anchor=GridBagConstraints.CENTER;  
	        gbc.fill=GridBagConstraints.HORIZONTAL;
	        gbc.insets=new Insets(0,0,0,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
			left.add(content,gbc);
			jsp.setViewportView(left);
			gbc.gridx=0;
	        gbc.gridy=0;
	        gbc.gridwidth=1;
	        gbc.gridheight=GridBagConstraints.REMAINDER;
	        gbc.weightx=0;
	        gbc.weighty=1;
	        gbc.anchor=GridBagConstraints.CENTER;  
	        gbc.fill=GridBagConstraints.VERTICAL;
	        gbc.insets=new Insets(0,0,0,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
			this.add(jsp,gbc);
			right = new JPanel();
			right.add(new ActionLabel("返回",70));
			gbc.gridx=GridBagConstraints.RELATIVE;
	        gbc.gridy=0;
	        gbc.gridwidth=1;
	        gbc.gridheight=GridBagConstraints.REMAINDER;
	        gbc.weightx=0;
	        gbc.weighty=1;
	        gbc.anchor=GridBagConstraints.CENTER;  
	        gbc.fill=GridBagConstraints.VERTICAL;
	        gbc.insets=new Insets(0,0,0,0);
	        gbc.ipadx=0;
	        gbc.ipady=0;
	        this.add(right,gbc);
		}
		public void repaint(){
			this.removeAll();
			left = null;
			title = null;
			gbc = null;
			content = null;
			right = null;
			this.paint();
		}
		public void reSet(RssItem item){
			this.setItem(item);
			this.revalidate();
			this.removeAll();
			left = null;
			title = null;
			gbc = null;
			content = null;
			right = null;
			this.paint();
			
		}
		public RssItem getItem() {
			return item;
		}
		public void setItem(RssItem item) {
			this.item = item;
		}
//		if(e.isMetaDown()){//检测鼠标右键单击
//			JLayeredPane layeredPane = (JLayeredPane)this.getParent();
//			layeredPane.moveToBack(this);
//		}
	}
	public class NewsLabel extends JLabel implements MouseListener{
		private String url = null;
		private Object item = null;
		public NewsLabel(String text,String url,RssItem item,int width){
			this.url = url;
			this.item = item;
			this.setText(text);  
			this.setForeground(Color.darkGray);//设置链接颜色  
			this.setPreferredSize(new Dimension(width,30));
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式  
			this.setToolTipText(url);//设置提示文字  
			this.addMouseListener(this);  
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JLayeredPane layeredPane = (JLayeredPane)this.getParent().getParent().getParent().getParent();
			MainPanel mainPanel = (MainPanel)layeredPane.getComponentsInLayer(100)[0];
			NewsContent content = (NewsContent)layeredPane.getComponentsInLayer(10)[0];
			content.reSet((RssItem)item);
			layeredPane.setLayer(content,new Integer(100));
			layeredPane.setLayer(mainPanel, new Integer(10));
		}
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) { }
		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
		public Object getItem() {
			return item;
		}
		public void setItem(Object item) {
			this.item = item;
		}
		public String getUrl() {
			return url;
		}
		
	}
	public class ActionLabel extends JLabel implements MouseListener{
		public ActionLabel(String text,int width){
			this.setText(text);  
			this.setForeground(Color.darkGray);//设置链接颜色  
			this.setPreferredSize(new Dimension(width,30));
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式  
			this.addMouseListener(this);  
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JLayeredPane layeredPane = (JLayeredPane)this.getParent().getParent().getParent();
			MainPanel mainPanel = (MainPanel)layeredPane.getComponentsInLayer(10)[0];
			NewsContent content = (NewsContent)layeredPane.getComponentsInLayer(100)[0];
			layeredPane.setLayer(content,new Integer(10));
			layeredPane.setLayer(mainPanel, new Integer(100));
		}

		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) { }
		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
		
	}
	public class LinkLabel extends JLabel implements MouseListener{
		private String url = null;
		public LinkLabel(String text,String url,int width){
			this.url = url;
			this.setText(text);  
			this.setForeground(Color.darkGray);//设置链接颜色  
			this.setPreferredSize(new Dimension(width,30));
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式  
			this.setToolTipText(url);//设置提示文字  
			this.addMouseListener(this);  
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println(this.url);
		}

		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) { }
		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
		
	}
	public static void main(String[] args){
		RssView rv = new RssView();
		rv.setVisible(true);
	}
}
