import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * @comment: 建立一個TabbedPane 並加入圖片
 * @author Han-Hong Wang
 * @date 2007/3/18
 */
public class JTabbedPaneDemo extends JFrame {
	public JTabbedPaneDemo() {
		// 建立一個TabbedPane
		JTabbedPane tabbed = new JTabbedPane();
//		tabbed.setPreferredSize(new Dimension(1024, 768));
		
		// 建立兩個Label  並加入Tab中
		JLabel label1 = new JLabel(getImage("1.png"));
		JLabel label2 = new JLabel(getImage("2.png"));
		tabbed.addTab("Image 1", getImage("database.png"), label1);
		tabbed.addTab("Image 2", getImage("file.png"), label2);
		
		// 將tabbed加入JFrame
		this.getContentPane().add(tabbed);
		this.setTitle("This is JTabbedPaneDemo.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);	// 最大化
		//this.pack();
		this.setVisible(true);
	}
	
	// 讀取圖片的函式
	public ImageIcon getImage(String imgName) {
		return new ImageIcon(this.getClass().getResource("./images/"+imgName));
	}

	public static void main(String[] args) {
		new JTabbedPaneDemo();
	}
}
