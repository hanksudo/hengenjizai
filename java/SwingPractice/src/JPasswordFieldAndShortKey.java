import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @comment: JLabel, JTextField, JPasswordField �M�ֱ���J�I�B��
 * @author Han-Hong Wang
 * @date 2007/5/2
 */

public class JPasswordFieldAndShortKey extends JFrame {

	public JPasswordFieldAndShortKey() {
		
		// �s�WTextField
		JTextField textID = new JTextField();
		textID.setBounds(260, 100, 80, 25);
		
		// �s�WJPasswordField
		JPasswordField textPwd = new JPasswordField();
		textPwd.setBounds(260, 150, 80, 25);
		
		// �s�WLabel
		JLabel labID = new JLabel("�b��(U)�G");
		labID.setBounds(200, 100, 60, 25);
		labID.setDisplayedMnemonic('U');	// �ֱ���
		labID.setLabelFor(textID);			// Focus
		
		JLabel labPwd = new JLabel("�K�X(P)�G");
		labPwd.setBounds(200, 150, 60, 25);
		labPwd.setDisplayedMnemonic('P');
		labPwd.setLabelFor(textPwd); 
		
		this.getContentPane().add(labID);
		this.getContentPane().add(labPwd);
		this.getContentPane().add(textID);
		this.getContentPane().add(textPwd);
		this.getContentPane().setLayout(null);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JPasswordFieldAndShortKey();
	}

}
