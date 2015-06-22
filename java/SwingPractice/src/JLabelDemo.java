import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @comment: JLabel Demo 
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JLabelDemo extends JFrame {
	public JLabelDemo() {
		/* 純文字 Label */
		JLabel label1 = new JLabel("This is Label 1.");	// 內容
		label1.setBounds(0, 0, 100, 30);	// 位置與大小
		
		/* 圖文 Label */
		JLabel label2 = new JLabel("This is Label 2 contain Image.");
		label2.setBounds(0, 30, 100, 50);
		URL imageURL = this.getClass().getResource("./images/middle.gif");			// 取得icon路徑
    	label2.setIcon(new ImageIcon(imageURL));						// 設置icon
    	
    	this.getContentPane().add(label1);	// Label加到 Frame裡
    	this.getContentPane().add(label2);
    	this.setSize(250, 130);
    	this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JLabelDemo();
	}
}
