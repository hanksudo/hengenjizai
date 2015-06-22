import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @comment: ²¾¶i«ö¶s(RollOver) §ïÅÜIcon
 * @author Han-Hong Wang
 * @date 2007/3/22
 */
public class JButtonRollOver extends JFrame {
	
	public JButtonRollOver() {
		JButton jb = new JButton("Roll Over");
		jb.setIcon(getImage("./images/middle.gif"));
		jb.setRolloverIcon(getImage("./images/database.png"));
		jb.setPressedIcon(getImage("./images/file.png"));
		jb.setRolloverEnabled(true);
		
		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(jb);
		this.setSize(200, 100);
		this.setVisible(true);
	}

	public ImageIcon getImage(String imgPath) {
		URL imageURL = this.getClass().getResource(imgPath);
		return new ImageIcon(imageURL);
	}
	
	public static void main(String[] args) {
		new JButtonRollOver();
	}

}
