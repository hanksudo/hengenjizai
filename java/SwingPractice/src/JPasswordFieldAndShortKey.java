import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @comment: JLabel, JTextField, JPasswordField 和快捷鍵焦點運用
 * @author Han-Hong Wang
 * @date 2007/5/2
 */

public class JPasswordFieldAndShortKey extends JFrame {

	public JPasswordFieldAndShortKey() {
		
		// 新增TextField
		JTextField textID = new JTextField();
		textID.setBounds(260, 100, 80, 25);
		
		// 新增JPasswordField
		JPasswordField textPwd = new JPasswordField();
		textPwd.setBounds(260, 150, 80, 25);
		
		// 新增Label
		JLabel labID = new JLabel("帳號(U)：");
		labID.setBounds(200, 100, 60, 25);
		labID.setDisplayedMnemonic('U');	// 快捷鍵
		labID.setLabelFor(textID);			// Focus
		
		JLabel labPwd = new JLabel("密碼(P)：");
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
