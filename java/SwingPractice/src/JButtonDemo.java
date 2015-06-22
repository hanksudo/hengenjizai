import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @comment: JButton Demo 加入 ActionListener
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JButtonDemo extends JFrame implements ActionListener {
	private static JLabel labStatus;
	public JButtonDemo() {
		
		JButton btnYes = new JButton("Yes");
		JButton btnNo = new JButton("No");
		btnYes.addActionListener(this);
		btnNo.addActionListener(this);
		
		labStatus = new JLabel("", JLabel.CENTER);
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(btnYes, BorderLayout.NORTH);
		cp.add(btnNo, BorderLayout.SOUTH);
		cp.add(labStatus, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);									// 位置置中
		this.setSize(200, 150);
		//this.pack();														// 自動調整視窗大小(視多少元件而定)
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		labStatus.setText("You click \""+e.getActionCommand()+"\"");
	}
	
	public static void main(String[] args) {
		new JButtonDemo();
	}

}
