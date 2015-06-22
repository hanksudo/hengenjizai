import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @comment: �إ� JFrame �ýվ�U���ݩ�
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JFrameDemo extends JFrame {
	public JFrameDemo() {
		this.setTitle("Welcome to JAVA planet!!");							// ���D
		this.setSize(350, 100);												// �j�p
		this.setLocationRelativeTo(null);									// ��m�m��
		//this.pack();														// �۰ʽվ�����j�p(���h�֤���өw)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);						// �w�]�����Ҧ�(���U�e�e��)
		
		URL imageURL = this.getClass().getResource("./images/middle.gif");			// ���oicon���|
		this.setIconImage(new ImageIcon(imageURL).getImage());				// �]�micon
		
		this.getContentPane().setBackground(Color.green);					// ����
		this.setVisible(true);												// �}�ҵ���
	}
	
	public static void main(String[] args) {
		new JFrameDemo();
	}
}