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
		f = new JFrame("�n�J�t��");
		
		// �s�WTextField
		textID = new JTextField();
		textID.setBounds(260, 100, 80, 25);
		
		// �s�WJPasswordField
		textPwd = new JPasswordField();
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
		
		// �[�J��ť��
		LoginInterface g = new LoginInterface();
		
		// �s�W���s
		JButton btnSubmit = new JButton("�e�X(S)");
		JButton btnClear = new JButton("�M��(C)");
		JButton btnExit = new JButton("���}(X)");
		
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
				JOptionPane.showMessageDialog(f,"�w��Ө찪���j!!","Welcome",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(f,"�b���αK�X���~, �q���Y�N�z��!!","WARNING",JOptionPane.WARNING_MESSAGE);
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
