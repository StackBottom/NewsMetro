package test;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * ��JLayeredPaneʹ�õ�С����
 *
 * @author �嶷�� <��ת���뱣�����ߺͳ���>
 * @blog http://blog.csdn.net/mq612
 */
public class Test extends JFrame implements ActionListener {

 private static final long serialVersionUID = 4785452373598819719L;

 private JButton b_a = null, b_b = null;

 private JLayeredPane lp = null; // ����Ҫ�õ��Ĳ�

 public Test() {
  super("JLayeredPane");

  lp = this.getLayeredPane(); // ��ȡJLayeredPane

  b_a = new JButton("A");
  b_b = new JButton("B");

  b_a.addActionListener(this); // ��ť�¼�
  b_b.addActionListener(this);

  lp.add(b_a, new Integer(200)); // �������ӵ�JLayeredPane�У�ָ�����ڵĲ�
  lp.add(b_b, new Integer(300));

  b_a.setBounds(new Rectangle(100, 100, 100, 100)); // Button����λ��
  b_a.setVisible(true); // ��ʾ

  b_b.setBounds(new Rectangle(50, 50, 100, 100));
  b_b.setVisible(true);

  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(360, 260);
  this.setVisible(true);
 }

 public void actionPerformed(ActionEvent e) {
  if (e.getActionCommand().equals("A")) { // �ж����ĸ���ť�Ķ���
   lp.setLayer(b_a, new Integer(300)); // ���������������
   lp.setLayer(b_b, new Integer(200));
  } else if (e.getActionCommand().equals("B")){
   lp.setLayer(b_a, new Integer(200));
   lp.setLayer(b_b, new Integer(300));
  }
 }

 public static void main(String args[]) {
  new Test();
 }

}