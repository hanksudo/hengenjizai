import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @comment: ���r�C�� Tic Tac Toe
 * @author Han-Hong Wang
 * @date 2007/3/23
 */
public class MainGui extends JFrame implements ActionListener {
	int player=0;	// �P�_�ثe���a 0=O 1=X
	int result=-1;	// ���ɵ��G
	char chess[] = {'O', 'X'};
	int record[] = new int[9];
	int times=0;	// �ثe�X��Q��
	JPanel btnPanel = new JPanel();
	JButton jb[] = new JButton[9];
	String title = "���r�C�� Tic Tac Toe";
	
	public MainGui() {
		
		//	�إߤE�c�� ����
		btnPanel.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jb[i].setActionCommand(String.valueOf(i));
			jb[i].setFocusable(false);
			jb[i].setBackground(Color.orange);
			record[i]=-1;
			btnPanel.add(jb[i]);	// �[�JBtnPanel
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.getContentPane().add(btnPanel);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(title+" - O ���U");
		this.setSize(300, 300);
		this.setVisible(true);
	}

	// Button Action
	public void actionPerformed(ActionEvent e) {
		int index = Integer.parseInt(e.getActionCommand());
		if (record[index]==-1 && (result==-1)) {
			jb[index].setText(Character.toString(chess[player]));
			record[index] = player;
			
			btnPanel.requestFocus();	// �Nfocus �]��@Panel
			player = 1-player;	// �����a
			this.setTitle(title+" - ���� "+chess[player]);
			times++;
			if (times>4) {checkWin();}
		}
	}
	
	
	// �P�w�ӭt
	private void checkWin() {
		int i;
		
		for (i=0; i<=2; i++) {
			// �P�_��V
			if ((record[i*3]==record[i*3+1]) && (record[i*3]==record[i*3+2])) {
				if (record[i*3]!=-1)result=record[i*3];
			}
			// �P�_�a�V
			if ((record[i]==record[i+3]) && (record[i]==record[i+6])) {
				if (record[i]!=-1)result=record[i];
			}
		}
		
		// �P�_�צV - ���W�ܥk�U
		if((record[0]==record[4])&&(record[0]==record[8])&&(record[0]!=-1)){result=record[0];}
		//�P�_�צV - �k�W�ܥ��U
		if((record[2]==record[4])&&(record[2]==record[6])&&(record[0]!=-1)){result=record[2];}

		// ���G�ӧQ
		if (result!=-1) {
			JOptionPane.showMessageDialog(this, "The Winner is "+chess[result], "�ӭt�w��", JOptionPane.INFORMATION_MESSAGE);
			this.setTitle(title+" - "+chess[result]+" win");
		} else {
			// ����
			if (times==9) {
				JOptionPane.showMessageDialog(this, "���襭��", "�ӭt�w��", JOptionPane.INFORMATION_MESSAGE);
				this.setTitle(title+" - ���襭��");
			}
		}
	}
	
	public static void main(String[] args) {
		new MainGui();
	}
}
