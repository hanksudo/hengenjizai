import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/**
 * @comment: �N����JLabel��Layer�ƦC��iJLayeredPane
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JLayerDemo extends JFrame {
	public JLayerDemo() {
		Color labelsColor[] = {Color.blue, Color.cyan, Color.green, Color.red, Color.orange};
		String labelsText[] = {"First", "Second", "Third", "Fourth", "Fifth"};
		JLabel labelObjs[] = new JLabel[5];
		Container cp = this.getContentPane();
		JLayeredPane jlp = new JLayeredPane();
		
		for (int i=0; i<=4; i++) {
			labelObjs[i] = new JLabel(labelsText[i]);
			labelObjs[i].setBackground(labelsColor[i]);		// ����
			labelObjs[i].setSize(100, 100);					// �j�p
			labelObjs[i].setLocation(i*50, i*50);			// ��m
			labelObjs[i].setOpaque(true);					// ���z��
			jlp.add(labelObjs[i], new Integer(i*100));		// �]�mLayer Position
		}
		
		cp.add(jlp);
		
		this.setSize(350,350);
		//this.setLocation(260, 190);
		this.setLocationRelativeTo(null);	// �m��
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
  
	public static void main(String[] args) {
	  new JLayerDemo();
	}
}
