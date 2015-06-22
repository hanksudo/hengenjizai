import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * @comment: �إߤ@��TabbedPane �å[�J�Ϥ�
 * @author Han-Hong Wang
 * @date 2007/3/18
 */
public class JTabbedPaneDemo extends JFrame {
	public JTabbedPaneDemo() {
		// �إߤ@��TabbedPane
		JTabbedPane tabbed = new JTabbedPane();
//		tabbed.setPreferredSize(new Dimension(1024, 768));
		
		// �إߨ��Label  �å[�JTab��
		JLabel label1 = new JLabel(getImage("1.png"));
		JLabel label2 = new JLabel(getImage("2.png"));
		tabbed.addTab("Image 1", getImage("database.png"), label1);
		tabbed.addTab("Image 2", getImage("file.png"), label2);
		
		// �Ntabbed�[�JJFrame
		this.getContentPane().add(tabbed);
		this.setTitle("This is JTabbedPaneDemo.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);	// �̤j��
		//this.pack();
		this.setVisible(true);
	}
	
	// Ū���Ϥ����禡
	public ImageIcon getImage(String imgName) {
		return new ImageIcon(this.getClass().getResource("./images/"+imgName));
	}

	public static void main(String[] args) {
		new JTabbedPaneDemo();
	}
}
