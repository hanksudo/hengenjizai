import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @comment: 井字遊戲 Tic Tac Toe
 * @author Han-Hong Wang
 * @date 2007/3/23
 */
public class TicTacToe extends JFrame implements ActionListener {
	GridLayout layout = new GridLayout(3,3);
	JPanel chessBoard = new JPanel();
	JButton btnArr[][] = new JButton[3][3];
	public char board[][] = new char[3][3];
	private char activeMark;
	String title = "井字遊戲 Tic Tac Toe";

	public TicTacToe() {
		// 新增MenuBar
		this.setJMenuBar(new createMenuBar());
		
		// 新增按鍵
		chessBoard.setLayout(layout);
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				btnArr[i][j] = new JButton();
				btnArr[i][j].setBackground(Color.white);
				btnArr[i][j].setFocusable(false);
				btnArr[i][j].setActionCommand(String.valueOf((i*3)+j));
				btnArr[i][j].addActionListener(this);
				chessBoard.add(btnArr[i][j]);
				board[i][j] = 0;
			}
		}
		NewGame();
		this.add(chessBoard);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setVisible(true);
	}

	// 選單列
	class createMenuBar extends JMenuBar implements ActionListener {
		public createMenuBar() {
			// 選單列
			
			JMenu jm = new JMenu("遊戲");
			
			JMenuItem jmi1 = new JMenuItem("新遊戲");
			JMenuItem jmi2 = new JMenuItem("結束遊戲");
			jmi1.setActionCommand("New");
			jmi2.setActionCommand("Exit");
			jmi1.addActionListener(this);
			jmi2.addActionListener(this);
			
			jm.add(jmi1);
			jm.add(jmi2);
			this.add(jm);
		}
		public void actionPerformed(ActionEvent evt) {
			if (evt.getActionCommand()=="New") {NewGame();}
			if (evt.getActionCommand()=="Exit") {System.exit(0);}
		}
	}
	
	public void NewGame() {	// 新遊戲
		activeMark='O';
		openBoard(true);
		int i, j;
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				btnArr[i][j].setText("");
				board[i][j] = 0;
			}
		}
		this.setTitle(title+" - 輪到 "+activeMark+" 下");
	}
	
	public void actionPerformed(ActionEvent evt) {
		int row, col;
		row = Integer.parseInt(evt.getActionCommand())/3;
		col = Integer.parseInt(evt.getActionCommand())%3;
		btnArr[row][col].setText(""+activeMark);
		btnArr[row][col].setEnabled(false);
		board[row][col] = activeMark;
		
		if (checkWin(activeMark)) {
			JOptionPane.showMessageDialog(this, "恭喜 "+activeMark+" 獲勝!!", "勝負已分", JOptionPane.INFORMATION_MESSAGE);
			this.setTitle(title+" - "+activeMark+" 獲勝!!");
			openBoard(false);
		} else if (!stillHaveSpace()) {
			JOptionPane.showMessageDialog(this, "雙方平手!!", "勝負已分", JOptionPane.INFORMATION_MESSAGE);
			this.setTitle(title+" - 雙方平手");
		} else {
			if (activeMark=='O') {activeMark='X';} else {activeMark='O';}
			this.setTitle(title+" - 輪到 "+activeMark+" 下");
		}
	}
	
	public boolean checkWin(char checkMark) {	// 判斷勝利者
		int i, j;
		// 判斷橫向
		for(i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				if (board[i][j]!=checkMark) {
					break;
				}
			}
			if (j>=3) {
				return true;
			} else {
				continue;
			}
		}
		
		// 判斷直向
		for(j=0; j<3; j++) {
			for (i=0; i<3; i++) {
				if (board[i][j]!=checkMark) {
					break;
				}
			}
			if (i>=3) {
				return true;
			} else {
				continue;
			}
		}
		
		// 判斷斜的 \ 方向
		for (i=0; i<3; i++) {
			if (board[i][i]!=checkMark) {
				break;
			}
		}
		if (i>=3) {return true;}
		
		// 判斷斜的 / 方向
		for (i=0,j=2; i<3; i++,j--) {
			if (board[i][j]!=checkMark) {
				break;
			}
		}
		if (i>=3) {return true;}
		
		return false;
	}
	
	public boolean stillHaveSpace() {	// 取得空格數
		int i, j;
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				if (board[i][j]==0) {return true;} 
			}
		}
		return false;
	}
	
	public void openBoard(boolean k) {	// 開/關 棋盤
		int i, j;
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				btnArr[i][j].setEnabled(k);
			}
		}
	}

	public static void main(String[] args) {
		new TicTacToe();
	}

}

