import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * @comment: 按下按鈕隨機改變按鈕圖片
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/21
 */
public class JButtonRandomImage implements ActionListener {
	JFrame myFrame;
	JButton btn;
	int r;
	public JButtonRandomImage() {
		myFrame = new JFrame("JButton Random Image");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(200, 100);
		myFrame.setLayout(new FlowLayout());
		
		btn = new JButton("Click Me");
		btn.addActionListener(this);
		
		myFrame.getContentPane().add(btn);
		myFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new JButtonRandomImage();
	}

	public void actionPerformed(ActionEvent e) {	
		r = (int)(Math.random()*7);
		URL imageURL = this.getClass().getResource("/images/"+r+".gif");
		btn.setText("Now Image: "+r+".gif");
		btn.setIcon(new ImageIcon(imageURL));
	}

}
