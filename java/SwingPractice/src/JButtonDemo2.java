import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @comment: JButton Demo ��JPanel�å[�J����, �å�GridLayput�ƪ�
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JButtonDemo2 extends JFrame implements ActionListener {
	private JPanel buttonPanel = new JPanel();
	
	public JButtonDemo2() {
		buttonPanel.setLayout(new GridLayout(1, 4, 4, 4));
		
		addButton("Sports", "./images/middle.gif", 'S');
		addButton("Movies", "./images/middle.gif", 'M');
		addButton("Travel", "./images/middle.gif", 'T');
		addButton("Art", "./images/middle.gif", 'A');
		
		this.getContentPane().add(buttonPanel);
		this.pack();
		this.setVisible(true);
	}
	
	public void addButton(String caption, String imgPath, char key) {
		URL imageURL = this.getClass().getResource(imgPath);
		JButton btn = new JButton(caption, new ImageIcon(imageURL));	// �s�W���s�å[�J�Ϥ�
		btn.setMnemonic(key);	// ����
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);	// ���s����r��������m
		btn.setHorizontalTextPosition(SwingConstants.CENTER); // ���s����r��������m
		btn.addActionListener(this);
		buttonPanel.add(btn);	//�N�����s�[�iButtonPanel��
	}
	
	public void actionPerformed(ActionEvent e) {
		System.err.println(e.getActionCommand());
	}
	
	public static void main(String[] args) {
		new JButtonDemo2();
	}
}
