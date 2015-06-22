

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 * @comment: 踩地雷 Minesweeper
 * @author Han-Hong Wang
 * @msn: phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/5/12
 */

public class Minesweeper {
	static bombButton btn[][] = new bombButton[9][9];
	JFrame myFrame;
	JPanel myPanel;
	private int Number_of_Bomb = 10;
	private boolean findR;
	static boolean GameOver;
	GameAction newAction, exitAction;
	
	public Minesweeper() {
		myFrame = new JFrame("Minesweeper - 踩地雷");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setSize(360, 360);
		myFrame.setJMenuBar(createMenuBar());
		// 取得系統目前解析度
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// 將視窗置中
		myFrame.setLocation((screen.width-myFrame.getWidth())/2, (screen.height-myFrame.getHeight())/2);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exc) {System.err.println("Error loading L&F: " + exc);}
		
		myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(9,9));
		
		init();
		
		myFrame.getContentPane().add(myPanel);
		myFrame.setVisible(true);
	}
	
	// Initialize
	public void init() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {								
				btn[j][i] = new bombButton(j,i);
				myPanel.add(btn[j][i]);
			}
		}
		setBomb();
		Calculate_Bomb_Count();
	}
	
	// 顯示所有按鈕狀態
	public static void showAllBombs() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {								
				btn[i][j].Clicked(true);
			}
		}
	}
	
	// 重置所有按鈕狀態
	public void resetAllButton() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {								
				btn[i][j].Clicked(false);
				btn[i][j].setBombState(0);	// 把炸彈狀態歸0
				btn[i][j].setBackground(new Color(236, 233, 216));
			}
		}
		GameOver = false;
		setBomb();
		Calculate_Bomb_Count();
	}
	
	// 產生亂數炸彈
	public void setBomb() {
		for (int i=0; i<Number_of_Bomb; i++) {
			findR = false;
			while(!findR) {
				int colR = (int)(Math.random()*9);
				int rowR = (int)(Math.random()*9);
				if (btn[rowR][colR].getBombState()==0) {btn[rowR][colR].setBombState(9);findR=true;}
			}
		}
	}
	
	// 計算某座標附近炸彈個數
	public void Calculate_Bomb_Count() {
		int count;
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {				
				// 如果本身是炸彈就不用判斷
				count = 0;
				if (btn[x][y].getBombState()!=9) {
					for (int i=-1; i<=1; i++) {
						for (int j=-1; j<=1; j++) {
							if ((i+x>=0 && i+x<9) && (j+y>=0 && j+y<9)) {
								if (btn[i+x][j+y].getBombState()==9) {count++;}
							}
						}
					}
					btn[x][y].setBombState(count);
				}
			}
		}
	}


	// 如果按到0個炸彈則擴展附近
	public static void Spread(int x, int y) {
		if (btn[x][y].getBombState()==0 && !btn[x][y].getClicked()) {
			btn[x][y].Clicked(true);			
//			if (x+1<=8) Spread(x+1, y);
//			if (y+1<=8) Spread(x, y+1);
//			if (x-1>=0) Spread(x-1, y);
//			if (y-1>=0) Spread(x, y-1);
//			if (x-1>=0 && y-1>=0) Spread(x-1, y-1);
//			if (x+1<=8 && y+1<=8) Spread(x+1, y+1);
//			if (x+1<=8 && y-1>=0) Spread(x+1, y-1);
//			if (x-1>=0 && y+1<=8) Spread(x-1, y+1);
			for (int i=-1; i<=1; i++) {
				for (int j=-1; j<=1; j++) {
					if ((i+x>=0 && i+x<9) && (j+y>=0 && j+y<9)) {
						Spread(i+x, j+y);
					}
				}
			}
		} else {
			btn[x][y].Clicked(true);
		}
	}
	
	/** Setup Menu Bar**/
	public JMenuBar createMenuBar() {
		// 建立 JMenuBar
		JMenuBar myMenu = new JMenuBar();
		
		// 建立JMenu
        String Menus[] = {"Game"};	//選單名稱
        char Mnemonics[] = {'G'};	// 快捷鍵
        JMenu[] mMenu = new JMenu[Menus.length];

        for (int i=0;i<Menus.length;i++) {
        	mMenu[i] = new JMenu(Menus[i]);	// Create Menu
        	mMenu[i].setMnemonic(Mnemonics[i]);	// Set Mnemonic
        	myMenu.add(mMenu[i]);	// Add to MenuBar
        }        
        
        // FirstMenu
        addMenuItem(mMenu[0], newAction = new GameAction("New Game", "Start a New Game", "ctrl N"));
        addMenuItem(mMenu[0], exitAction = new GameAction("Exit", "Exit the Game", "alt X"));
        
        return myMenu;
	}
	
	private JMenuItem addMenuItem(JMenu menu, Action action){
		JMenuItem myItem = menu.add(action);
		return myItem;
	}
	
	/** Setup All Actions**/
	class GameAction extends AbstractAction {
		public GameAction(String name, String tooltip, String keystroke) {
			super(name);
			
			// 設置提示
			if(tooltip != null) { putValue(SHORT_DESCRIPTION, tooltip); }
			
			// 設置快捷鍵
			if(keystroke != null) { putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(keystroke)); }
		}

		public void actionPerformed(ActionEvent e) {
			if (this==newAction) {resetAllButton();}
			if (this==exitAction) {myFrame.dispose(); System.exit(1);}
		}	
	}
	
	public static void main(String[] args) {
		new Minesweeper();
	}
}
