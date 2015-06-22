import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @comment: JTextField 範例
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JTextFieldDemo {
	public static void main(String[] args) {
		JFrame f = new JFrame("JTextFieldDemo");
		
		// 取得螢幕解析度
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// 視窗置中
		f.setLocation((screen.width-800)/2, (screen.height-600)/2);
		
		// 新增Label
		JLabel labName = new JLabel("姓名：");
		labName.setBounds(200, 200, 50, 25);
		
		// 新增TextField
		JTextField textName = new JTextField();
		textName.setBounds(260, 200, 80, 25);
		
		f.getContentPane().add(labName);
		f.getContentPane().add(textName);
		f.getContentPane().setLayout(null);
		f.setSize(800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
