import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginInterface implements ActionListener {
	static JTextField textID;
	static JPasswordField textPwd;
	static JFrame f;
	
	public static void main(String[] args) {
		f = new JFrame("登入系統");
		
		// 新增TextField
		textID = new JTextField();
		textID.setBounds(260, 100, 80, 25);
		
		// 新增JPasswordField
		textPwd = new JPasswordField();
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
		
		// 加入傾聽器
		LoginInterface g = new LoginInterface();
		
		// 新增按鈕
		JButton btnSubmit = new JButton("送出(S)");
		JButton btnClear = new JButton("清除(C)");
		JButton btnExit = new JButton("離開(X)");
		
		btnSubmit.setMnemonic('S');
		btnClear.setMnemonic('C');
		btnExit.setMnemonic('X');
		
		btnSubmit.setBounds(180, 200, 80, 25);
		btnClear.setBounds(270, 200, 80, 25);
		btnExit.setBounds(360, 200, 80, 25);
		
		btnSubmit.addActionListener(g);
		btnClear.addActionListener(g);
		btnExit.addActionListener(g);
		
		btnSubmit.setActionCommand("Submit");
		btnClear.setActionCommand("Clear");
		btnExit.setActionCommand("Exit");
		
		f.getContentPane().add(labID);
		f.getContentPane().add(labPwd);
		f.getContentPane().add(textID);
		f.getContentPane().add(textPwd);
		f.getContentPane().add(btnSubmit);
		f.getContentPane().add(btnClear);
		f.getContentPane().add(btnExit);
		f.getContentPane().setLayout(null);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Submit")) {
			if ((textID.getText().equals("root")) && (isCorrectPwd(textPwd.getPassword()))) {
				JOptionPane.showMessageDialog(f,"歡迎來到高應大!!","Welcome",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(f,"帳號或密碼錯誤, 電腦即將爆炸!!","WARNING",JOptionPane.WARNING_MESSAGE);
				ClearFields();
			}
		}

		if (e.getActionCommand().equals("Clear")) {
			ClearFields();
		}

		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}
	
	public static void ClearFields() {
		textID.setText("");
		textPwd.setText("");
		textID.requestFocus();
	}
	
	public static boolean isCorrectPwd(char[] input) {
		boolean isCorrect = true;
		
		char[] correctPassword = {'t', 'e', 's', 't'};

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            for (int i=0; i<input.length; i++) {
                if (input[i] != correctPassword[i]) {
                    isCorrect = false;
                }
            }
        }

        //Zero out the password.
        for (int i = 0; i < correctPassword.length; i++) {
            correctPassword[i] = 0;
        }
		return isCorrect;
	}
}
