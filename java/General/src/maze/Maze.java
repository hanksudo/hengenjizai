package maze;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

/**
 * @comment: 迷宮路徑搜尋 Maze
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/5/28
 */
public class Maze extends JFrame {
	// 移動順序
	int moveX[] = {1, 0, -1, 0};
	int moveY[] = {0, 1, 0, -1};

	// 終點
	int goalX = 7;
	int goalY = 7;
	
	int walk_times = 0;	// 走的格數
	private JPanel shapePanel;
	int max;
	int maze[][];
	int maze_temp[][];
	SquareShape roadShape[][];
	MazeAction startAction, exitAction;
	private String mazeFilePath = "Maze2.txt";
	
	public Maze() throws Exception {
		super("Maze 迷宮路徑搜尋");
		shapePanel = new JPanel();
		shapePanel.setLayout(new GridLayout(8, 8));
		shapePanel.setBackground(Color.white);
		
		init();
		buildWall();
		
		this.getContentPane().add(shapePanel);
		this.setJMenuBar(createMenuBar());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);	
	}
	
	// 讀取迷宮檔並配置陣列大小
	public void init() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(this.getClass().getResource(mazeFilePath).getPath()));
		max = Integer.parseInt(br.readLine());
		
		maze = new int[max][max];
		maze_temp = new int[max][max];
		roadShape = new SquareShape[max][max];
		
		int i=0;
		while(br.ready()) {
			String temp[] = br.readLine().split(" ");
			for (int j=0; j<max; j++) {
				maze[j][i] = Integer.parseInt(temp[j]);
			}
			i++;
		}
		br.close();
	}
	
	// 建立牆壁
	public void buildWall() {
		for (int y=0; y<max; y++) {
			for (int x=0; x<max; x++) {
				shapePanel.add(roadShape[x][y] = new SquareShape(maze[x][y],""));
			}
		}
	}
	
	// 顯示結果
	public void showResult() {
		for (int x=0; x<max; x++) {
			for (int y=0; y<max; y++) {
				System.err.print(" "+maze_temp[y][x]);
				roadShape[x][y].setColor(maze_temp[x][y]);
			}
			System.err.println();
		}
		System.err.println();
	}
	
	// 找尋終點
	public void findRoad(int x, int y) {
		int moveTimes=0, h, v;
		while (moveTimes<=3) {
			h = x + moveX[moveTimes];
			v = y + moveY[moveTimes];
			
			if (h>=0 && h<max && v>=0 && v<max) {  // 判斷是否超出邊界
				if (maze[h][v]==0) {  // 如果為路即移動
					maze[h][v]=2;
					if (h==goalX && v==goalY) { // 抵達終點
						walk_count();	// 計算走的格數
						showResult();
						if (walk_times != 8*2-1) {maze[h][v]=0;} // 清除以利下一個遞迴搜尋
					} else {
						findRoad(h,v);
						maze[h][v]=0; // 清除以利下一個遞迴搜尋
					}
				}
			}
			moveTimes++;
		}
	}
		
	public void walk_count() {
		int k = 0;
		for (int i=0; i<max; i++) {
			for (int j=0; j<max; j++) {
				if (maze[i][j]==2) {k++;}
			}
		}
		
		if (walk_times==0 || walk_times > k) {
			walk_times = k;	// 記錄走的格數
			
			// 暫存目前陣列
			for (int i=0; i<max; i++) {
				for (int j=0; j<max; j++) {
					maze_temp[i][j]=maze[i][j];
				}
			}
		}
	}
	
	/** Setup Menu Bar**/
	public JMenuBar createMenuBar() {
		// 建立 JMenuBar
		JMenuBar myMenu = new JMenuBar();
		
		// 建立JMenu
        String Menus[] = {"Maze"};	//選單名稱
        char Mnemonics[] = {'M'};	// 快捷鍵
        JMenu[] mMenu = new JMenu[Menus.length];

        for (int i=0;i<Menus.length;i++) {
        	mMenu[i] = new JMenu(Menus[i]);	// Create Menu
        	mMenu[i].setMnemonic(Mnemonics[i]);	// Set Mnemonic
        	myMenu.add(mMenu[i]);	// Add to MenuBar
        }        
        
        // FirstMenu
        addMenuItem(mMenu[0], startAction = new MazeAction("Start Search", "Start search a path.", "ctrl S"));
//        addMenuItem(mMenu[0], showAction = new MazeAction("Show Path", "Show the shortest path.", "alt S"));
        addMenuItem(mMenu[0], exitAction = new MazeAction("Exit", "Exit the Game", "alt X"));
        
        return myMenu;
	}
	
	private JMenuItem addMenuItem(JMenu menu, Action action){
		JMenuItem myItem = menu.add(action);
		return myItem;
	}
	
	/** Setup All Actions**/
	class MazeAction extends AbstractAction {
		public MazeAction(String name, String tooltip, String keystroke) {
			super(name);
			
			// 設置提示
			if(tooltip != null) { putValue(SHORT_DESCRIPTION, tooltip); }
			
			// 設置快捷鍵
			if(keystroke != null) { putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(keystroke)); }
		}

		public void actionPerformed(ActionEvent e) {
			if (this==startAction) {maze[0][0]=2; findRoad(0,0);}
//			if (this==showAction) {showResult();}
			if (this==exitAction) {Maze.this.dispose(); System.exit(1);}
		}	
	}

	public static void main(String[] args) throws Exception {
		new Maze();
	}
	
	class SquareShape extends JLabel {		
		public SquareShape(int type, String text) {
			setColor(type);
			this.setText(text);
			this.setBorder(new BevelBorder(BevelBorder.LOWERED));
			this.setOpaque(true);
		}
		
		// 設置方塊顏色 0-路 1-牆 2-走的路
		private void setColor(int type) {
			switch (type) {
				case 0: this.setBackground(Color.white);break;
				case 1: this.setBackground(Color.black);break;
				case 2: this.setBackground(Color.red);break;
			}
		}
	}
}
