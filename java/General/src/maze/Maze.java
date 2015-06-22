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
 * @comment: �g�c���|�j�M Maze
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/5/28
 */
public class Maze extends JFrame {
	// ���ʶ���
	int moveX[] = {1, 0, -1, 0};
	int moveY[] = {0, 1, 0, -1};

	// ���I
	int goalX = 7;
	int goalY = 7;
	
	int walk_times = 0;	// �������
	private JPanel shapePanel;
	int max;
	int maze[][];
	int maze_temp[][];
	SquareShape roadShape[][];
	MazeAction startAction, exitAction;
	private String mazeFilePath = "Maze2.txt";
	
	public Maze() throws Exception {
		super("Maze �g�c���|�j�M");
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
	
	// Ū���g�c�ɨðt�m�}�C�j�p
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
	
	// �إ����
	public void buildWall() {
		for (int y=0; y<max; y++) {
			for (int x=0; x<max; x++) {
				shapePanel.add(roadShape[x][y] = new SquareShape(maze[x][y],""));
			}
		}
	}
	
	// ��ܵ��G
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
	
	// ��M���I
	public void findRoad(int x, int y) {
		int moveTimes=0, h, v;
		while (moveTimes<=3) {
			h = x + moveX[moveTimes];
			v = y + moveY[moveTimes];
			
			if (h>=0 && h<max && v>=0 && v<max) {  // �P�_�O�_�W�X���
				if (maze[h][v]==0) {  // �p�G�����Y����
					maze[h][v]=2;
					if (h==goalX && v==goalY) { // ��F���I
						walk_count();	// �p�⨫�����
						showResult();
						if (walk_times != 8*2-1) {maze[h][v]=0;} // �M���H�Q�U�@�ӻ��j�j�M
					} else {
						findRoad(h,v);
						maze[h][v]=0; // �M���H�Q�U�@�ӻ��j�j�M
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
			walk_times = k;	// �O���������
			
			// �Ȧs�ثe�}�C
			for (int i=0; i<max; i++) {
				for (int j=0; j<max; j++) {
					maze_temp[i][j]=maze[i][j];
				}
			}
		}
	}
	
	/** Setup Menu Bar**/
	public JMenuBar createMenuBar() {
		// �إ� JMenuBar
		JMenuBar myMenu = new JMenuBar();
		
		// �إ�JMenu
        String Menus[] = {"Maze"};	//���W��
        char Mnemonics[] = {'M'};	// �ֱ���
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
			
			// �]�m����
			if(tooltip != null) { putValue(SHORT_DESCRIPTION, tooltip); }
			
			// �]�m�ֱ���
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
		
		// �]�m����C�� 0-�� 1-�� 2-������
		private void setColor(int type) {
			switch (type) {
				case 0: this.setBackground(Color.white);break;
				case 1: this.setBackground(Color.black);break;
				case 2: this.setBackground(Color.red);break;
			}
		}
	}
}
