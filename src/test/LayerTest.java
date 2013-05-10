package test;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LayerTest extends JFrame {
	private JLayeredPane layer = null; // 我们要用到的层
	public LayerTest(){
		super();
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layer = this.getLayeredPane();
		NewsPanel panel0 = new NewsPanel();
		LinkLabel lable1 = new LinkLabel("layer0",null,70);
		panel0.add(lable1);
		NewsPanel panel1 = new NewsPanel();
		LinkLabel lable2 = new LinkLabel("layer-1",null,70);
		panel1.add(lable2);
		panel0.setBounds(new Rectangle(0,0,300,300));
		panel1.setBounds(new Rectangle(0,0,300,300));
		layer.add(panel0,new Integer(1));
		layer.add(panel1,new Integer(0));
		this.setVisible(true);
	}
	public class NewsPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 273217772717316582L;
		public NewsPanel(){}
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
			JLayeredPane layer = (JLayeredPane)this.getParent().getParent();
			JPanel panel0 = (JPanel)layer.getComponentsInLayer(0)[0];
			JPanel panel1 = (JPanel)layer.getComponentsInLayer(1)[0];
			layer.setLayer(panel0,new Integer(1));
			layer.setLayer(panel1,new Integer(0));
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
	public static void main(String args[]){
		new LayerTest();
	}
}
