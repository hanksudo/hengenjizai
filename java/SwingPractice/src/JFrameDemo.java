import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @comment: 建立 JFrame 並調整各項屬性
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JFrameDemo extends JFrame {
	public JFrameDemo() {
		this.setTitle("Welcome to JAVA planet!!");							// 標題
		this.setSize(350, 100);												// 大小
		this.setLocationRelativeTo(null);									// 位置置中
		//this.pack();														// 自動調整視窗大小(視多少元件而定)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);						// 預設關閉模式(按下叉叉時)
		
		URL imageURL = this.getClass().getResource("./images/middle.gif");			// 取得icon路徑
		this.setIconImage(new ImageIcon(imageURL).getImage());				// 設置icon
		
		this.getContentPane().setBackground(Color.green);					// 底色
		this.setVisible(true);												// 開啟視窗
	}
	
	public static void main(String[] args) {
		new JFrameDemo();
	}
}