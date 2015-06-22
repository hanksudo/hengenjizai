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
 * @comment: 井字遊戲 Tic Tac Toe
 * @author Han-Hong Wang
 * @date 2007/3/23
 */
public class MainGui extends JFrame implements ActionListener {
	int player=0;	// 判斷目前玩家 0=O 1=X
	int result=-1;	// 比賽結果
	char chess[] = {'O', 'X'};
	int record[] = new int[9];
	int times=0;	// 目前幾格被按
	JPanel btnPanel = new JPanel();
	JButton jb[] = new JButton[9];
	String title = "井字遊戲 Tic Tac Toe";
	
	public MainGui() {
		
		//	建立九宮格 按鍵
		btnPanel.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++) {
			jb[i] = new JButton();
			jb[i].addActionListener(this);
			jb[i].setActionCommand(String.valueOf(i));
			jb[i].setFocusable(false);
			jb[i].setBackground(Color.orange);
			record[i]=-1;
			btnPanel.add(jb[i]);	// 加入BtnPanel
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.getContentPane().add(btnPanel);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(title+" - O 先下");
		this.setSize(300, 300);
		this.setVisible(true);
	}

	// Button Action
	public void actionPerformed(ActionEvent e) {
		int index = Integer.parseInt(e.getActionCommand());
		if (record[index]==-1 && (result==-1)) {
			jb[index].setText(Character.toString(chess[player]));
			record[index] = player;
			
			btnPanel.requestFocus();	// 將focus 設於　Panel
			player = 1-player;	// 換玩家
			this.setTitle(title+" - 輪到 "+chess[player]);
			times++;
			if (times>4) {checkWin();}
		}
	}
	
	
	// 判定勝負
	private void checkWin() {
		int i;
		
		for (i=0; i<=2; i++) {
			// 判斷橫向
			if ((record[i*3]==record[i*3+1]) && (record[i*3]==record[i*3+2])) {
				if (record[i*3]!=-1)result=record[i*3];
			}
			// 判斷縱向
			if ((record[i]==record[i+3]) && (record[i]==record[i+6])) {
				if (record[i]!=-1)result=record[i];
			}
		}
		
		// 判斷斜向 - 左上至右下
		if((record[0]==record[4])&&(record[0]==record[8])&&(record[0]!=-1)){result=record[0];}
		//判斷斜向 - 右上至左下
		if((record[2]==record[4])&&(record[2]==record[6])&&(record[0]!=-1)){result=record[2];}

		// 公佈勝利
		if (result!=-1) {
			JOptionPane.showMessageDialog(this, "The Winner is "+chess[result], "勝負已分", JOptionPane.INFORMATION_MESSAGE);
			this.setTitle(title+" - "+chess[result]+" win");
		} else {
			// 平手
			if (times==9) {
				JOptionPane.showMessageDialog(this, "雙方平手", "勝負已分", JOptionPane.INFORMATION_MESSAGE);
				this.setTitle(title+" - 雙方平手");
			}
		}
	}
	
	public static void main(String[] args) {
		new MainGui();
	}
}
