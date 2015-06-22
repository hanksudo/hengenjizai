import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @comment: 主畫面表單
 * @author Han-Hong Wang
 * @date 2007/5/5
 */

public class mainFrame implements Constants {
	private JFrame myFrame;
	private DrawFrame drawFrame;
	private JDesktopPane desktop;
	private JCheckBoxMenuItem snap;
	
	private static ArrayList frameIndex = new ArrayList();	// 內部視窗索引值
	private static frameData frameContent;	// 內部視窗內容監控
	
	private static int Mode = SELECT; // 預設功能

	private FileAction newAction, openAction, closeAction, closeallAction, saveAction, saveallAction, saveasAction, exitAction;
	private ViewAction snapAction;
	private FuncAction selectAction, arrowAction, circleAction, ellipseAction, squareAction, rectangleAction, roundrectangleAction,
					   triangleAction, starAction, sixpolygonAction;
	private EditAction cutAction, copyAction, pasteAction, deleteAction;
	private DatabaseAction searchAction;
	private HelpAction aboutAction;
	
	UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();	// 讀取所有安裝的Look and Feel
	ViewAction lafAction[] = new ViewAction[laf.length];
	ViewAction gSizeAction[] = new ViewAction[5];
	
	public mainFrame(String frameTitle) {
		myFrame = new JFrame(frameTitle);
		
		// 使用目前系統外觀 Get System LookAndFeel
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(laf[DEFAULT_LaF].getClassName());	// 預設Theme
		} catch (Exception exc) {
			System.err.println("Error loading L&F: " + exc);
		}
		
		// 取得系統目前解析度
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// 設置視窗大小
		Dimension frameSize = new Dimension(1024, 768);
		myFrame.setSize(frameSize);
		
		// 將視窗置中
		myFrame.setLocation((int)(screen.width-frameSize.getWidth())/2, (int)(screen.height-frameSize.getHeight())/2);
		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 預設關閉模式
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitAction.actionPerformed(null);
			}
		});
		
		// 設置 JFrame Icon
		//URL imageURL = ClassLoader.class.getResource("/Images/icon.png");
		//f.setIconImage(new ImageIcon(imageURL).getImage());		
		
		desktop = new JDesktopPane();
		// 設置DesktopPane拖曳模式
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		desktop.setBackground(Color.black);

		// 將DesktopPane放入Frame
		myFrame.getContentPane().add(desktop);
		
		// 設置MenuBar
		myFrame.setJMenuBar(createMenuBar());
		
		// 設置ToolBar
		myFrame.getContentPane().add(ToolBar_General(), BorderLayout.NORTH);
		myFrame.getContentPane().add(ToolBar_DrawObject(), BorderLayout.WEST);
		
		// 預設格線大小
		Grid.changeGridSize(DEFAULT_GRID_SIZE);
		
		// 啟動 JFrame
		myFrame.setVisible(true);
		
		// 建立新檔
		createNewFile();
	}
	
	/** Create Menu Bar **/
	public JMenuBar createMenuBar() {
		// 建立 JMenuBar
		JMenuBar myMenu = new JMenuBar();
		
		// 建立JMenu
        String Menus[] = {"File","Edit","View","Function","Database","Help"};	//選單名稱
        char Mnemonics[] = {'F','E','V','U','D','H'};	// 快捷鍵
        JMenu[] mMenu = new JMenu[Menus.length];

        for (int i=0;i<Menus.length;i++) {
        	mMenu[i] = new JMenu(Menus[i]);	// Create Menu
        	mMenu[i].setMnemonic(Mnemonics[i]);	// Set Mnemonic
        	myMenu.add(mMenu[i]);	// Add to MenuBar
        }        
        
        // FirstMenu - File Menu
        addMenuItem(mMenu[0], newAction = new FileAction("New","Create a new file.","ctrl N",'N'));
        addMenuItem(mMenu[0], openAction = new FileAction("Open","Open a file.","ctrl O",'O'));
        addMenuItem(mMenu[0], closeAction = new FileAction("Close","Close current file.","alt C",'C'));
        addMenuItem(mMenu[0], closeallAction = new FileAction("Close All","Close all files.","",'E'));
        mMenu[0].addSeparator();
        addMenuItem(mMenu[0], saveAction = new FileAction("Save","Save current file.","ctrl S",'S'));
        addMenuItem(mMenu[0], saveallAction = new FileAction("Save All","Save all files.","",'L'));
        addMenuItem(mMenu[0], saveasAction = new FileAction("Save As...","Save As...","",'A'));
        mMenu[0].addSeparator();
        addMenuItem(mMenu[0], exitAction = new FileAction("Exit","Close this program.","alt F4",'X'));
        
        // SecondMenu - Edit Menu
        addMenuItem(mMenu[1], cutAction = new EditAction("Cut", "Cut","ctrl X"));
        addMenuItem(mMenu[1], copyAction = new EditAction("Copy", "Copy","ctrl C"));
        addMenuItem(mMenu[1], pasteAction = new EditAction("Paste", "Paste","ctrl V"));
        addMenuItem(mMenu[1], deleteAction = new EditAction("Delete", "Delete","DELETE"));
        
        // ThirdMenu - View Menu
        	// subMenu - Change Grid Size
        	boolean selected=false;
        	ButtonGroup gridGroup = new ButtonGroup();
        	JMenu gSizeMenu = new JMenu(new ViewAction("Grid Size", "Change Grid Size", null));
        	gSizeMenu.setMnemonic('G');
        	
        	addRadioMenuItem(gSizeMenu, gSizeAction[0] = new ViewAction("Disable Grid", "Disable Grid", "ctrl 0"), gridGroup, selected);
        	gSizeMenu.addSeparator();
        	for (int i = 1; i < 5; i++) {
        		if (i==2) {selected=true;} else {selected=false;}
        		addRadioMenuItem(gSizeMenu, gSizeAction[i] = new ViewAction(String.valueOf(i), "Change grid size to "+i, "ctrl "+String.valueOf(i)), gridGroup, selected);
    		}
        // Snap Menu
        snap = new JCheckBoxMenuItem(snapAction = new ViewAction("Snap to Grid", "Snap to Grid", "ctrl shift QUOTE"));
        snap.setSelected(true);
        snap.setMnemonic('S');
        	
        	// subMenu - Change Theme Style
        	ButtonGroup LaFGroup = new ButtonGroup();
    		JMenu LaFMenu = new JMenu(new ViewAction("Theme Style", "Change Theme Style", null));
    		LaFMenu.setMnemonic('T');
    		for (int i = 0; i < laf.length; i++) {
    			if (i==DEFAULT_LaF) {selected=true;} else {selected=false;}
    			addRadioMenuItem(LaFMenu, lafAction[i] = new ViewAction(laf[i].getName(), "Change to "+laf[i].getName(), ""), LaFGroup, selected);
    		}
    	
    		// 將sub Menu 加入View Menu	
    		mMenu[2].add(gSizeMenu);
    	mMenu[2].add(snap);
    	mMenu[2].addSeparator();
    		mMenu[2].add(LaFMenu);
 
       
        // Third Menu - Function Menu
    	ButtonGroup funcGroup = new ButtonGroup();
    	addRadioMenuItem(mMenu[3], selectAction = new FuncAction("Select", SELECT, "Select Mode","S"), funcGroup, true);
    	addRadioMenuItem(mMenu[3], arrowAction = new FuncAction("Arrow", ARROW, "Arrow Mode","A"), funcGroup, false);
    	mMenu[2].addSeparator();
    	addRadioMenuItem(mMenu[3], circleAction = new FuncAction("Circle", CIRCLE, "Circle Mode","C"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], ellipseAction = new FuncAction("Ellipse", ELLIPSE, "Ellipse Mode","E"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], squareAction = new FuncAction("Square", SQUARE, "Square Mode","Q"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], rectangleAction = new FuncAction("Rectangle", RECTANGLE, "Rectangle Mode","R"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], roundrectangleAction = new FuncAction("Round Rectangle", ROUNDRECTANGLE, "RoundRectangle Mode","D"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], triangleAction = new FuncAction("Triangle", TRIANGLE, "Triangle Mode","T"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], starAction = new FuncAction("Star", STAR, "Star Mode","V"), funcGroup, false);
    	addRadioMenuItem(mMenu[3], sixpolygonAction = new FuncAction("Six Polygon", SIXPOLYGON, "Six Polygon Mode","P"), funcGroup, false);
    	
    	// Fourth Menu - Database Menu
    	addMenuItem(mMenu[4], searchAction = new DatabaseAction("Search", "Search this object","alt S"));
    	
    	// Fifth Menu - Help Menu
    	addMenuItem(mMenu[5], aboutAction = new HelpAction("About", "About this program.",""));
    	
		return myMenu;
	}
	
	private void addMenuItem(JMenu menu, Action action) {
		menu.add(action);
	}
	
	private void addRadioMenuItem(JMenu menu, Action action, ButtonGroup group, boolean SelectedButton){
		JRadioButtonMenuItem myItem = new JRadioButtonMenuItem(action);
		menu.add(myItem);
		group.add(myItem);
		myItem.setSelected(SelectedButton);
	}

	/** Create Tool Bar **/
	public JToolBar ToolBar_General() {
		JToolBar myToolBar = new JToolBar();
		myToolBar.setFloatable(false);	// 禁止ToolBar浮動

		// File Action ToolBar
		addButton(myToolBar, newAction);
		addButton(myToolBar, openAction);
		addButton(myToolBar, saveAction);
		myToolBar.addSeparator();
		addButton(myToolBar, cutAction);
		addButton(myToolBar, copyAction);
		addButton(myToolBar, pasteAction);
		addButton(myToolBar, deleteAction);
		myToolBar.addSeparator();
		addButton(myToolBar, aboutAction);
		addButton(myToolBar, exitAction);
		
		
		// 禁止按鈕取得焦點
		for(int i=0; i<myToolBar.getComponentCount(); i++) {
			myToolBar.getComponent(i).setFocusable(false);
		}

		return myToolBar;
	}
	
	public JToolBar ToolBar_DrawObject() {
		JToolBar myToolBar = new JToolBar();
		myToolBar.setFloatable(true);	// 設置ToolBar可浮動(True/False)

		// File Action ToolBar
		addButton(myToolBar, selectAction);
		addButton(myToolBar, arrowAction);
		myToolBar.addSeparator();
		addButton(myToolBar, circleAction);		
		addButton(myToolBar, ellipseAction);
		addButton(myToolBar, squareAction);		
		addButton(myToolBar, rectangleAction);
		addButton(myToolBar, roundrectangleAction);
		addButton(myToolBar, triangleAction);
		addButton(myToolBar, starAction);
		addButton(myToolBar, sixpolygonAction);

		
		// 禁止按鈕取得焦點
		for(int i=0; i<myToolBar.getComponentCount(); i++) {
			myToolBar.getComponent(i).setFocusable(false);
		}

		// change JToolBar VERTICAL direction
		myToolBar.setOrientation(1);
		
		return myToolBar;
	}
	
	private void addButton(JToolBar toolBar, SetAction action) {
		// If it can "Toggle" Then use JToggleButton else use JButton.
		if (action.getValue("selected") != null) {
			toolBar.add(new ToggleButton(action));
		} else {
			JButton btn = new JButton(action);
			btn.setText("");
			toolBar.add(btn); 
		}
	}
	
	class ToggleButton extends JToggleButton implements PropertyChangeListener {
		public ToggleButton(Action a) {
			super(a);
			this.setText("");	// 將文字消除
			a.addPropertyChangeListener(this);
		}
		
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getPropertyName()=="selected") {
				Boolean b=(Boolean)evt.getNewValue();
				if (b!=null) { setSelected(b.booleanValue()); }
			}
		}
	}
	
	
	// 新增視窗 - 開新檔案
	public void createNewFile() {
		// 取得目前視窗數
		int freeSpace = getFreeSpace();
		
		// 取得物件索引
		drawFrame = setObjectsIndex(freeSpace);

		// 新增檔案時偏移位置
		drawFrame.setLocation(30*(freeSpace%10)+30*(freeSpace/10), 30*(freeSpace%10));
				
		// 將內部視窗加入DesktopPane管理
		desktop.add(drawFrame);
		
		// 取得新檔案焦點
		try {
			drawFrame.setSelected(true);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	/** Frame 物件索引 監控 **/
	// 定義目前 frame中內容
	private static class frameData {
		public DrawFrame drawFrame;
	}
	
	// 取得空的物件索引 重覆使用
	public static int getFreeSpace() {
		int freeSpace = -1;
		
		for (int i=0; i<frameIndex.size(); i++) {
			frameContent = (frameData)(frameIndex.get(i));
			if (frameContent.drawFrame==null) {
				freeSpace = i;break;
			}
		}
		
		if (freeSpace == -1) {
			frameIndex.add(new frameData());
			freeSpace = frameIndex.size()-1;
		}
		
		return freeSpace;
	}
	
	// 設置DrawFrame物件索引 
	public DrawFrame setObjectsIndex(int index) {
	    if(index<0) { return null; }	    
	    frameContent=(frameData)(frameIndex.get(index));	    
	    if(frameContent.drawFrame==null) { frameContent.drawFrame=new DrawFrame("BioPathWay "+(index+1)); }
	    return frameContent.drawFrame;
	}
	
	// 檢查所有已開啟的檔案是否存檔
	public boolean checkSaveAll() {
		boolean allSaved = true;
		JInternalFrame frames[] = desktop.getAllFrames();
		int i=0;
		while (allSaved && i<frames.length) {
			DrawFrame df = (DrawFrame)frames[i];
			allSaved = df.checkSave();
			i++;
		}
		return allSaved;
	}
	
	// 取得目前選取的Frame	
	public DrawFrame getCurrentFrame() {
		return (DrawFrame)desktop.getSelectedFrame();
	}
	
	/** 功能模式 **/
	// 設置目前功能模式
	public void setMode(int m) {
		Mode = m;
	}
	
	// 取得目前功能模式
	public static int getMode() {
		return Mode; 
	}
	
	/** Setup All Actions**/
	class FileAction extends SetAction {
		public FileAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}
		
		public FileAction(String name, String tooltip, String keystroke, char mnemonic) {
			super(name, tooltip, keystroke, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			drawFrame = getCurrentFrame();
			if (this==newAction) {createNewFile();}
			if (this==saveAction) {drawFrame.saveFile();}
			if (this==closeAction && drawFrame!=null) {drawFrame.checkSave();}
			if (this==exitAction) {if(checkSaveAll()) {myFrame.dispose();};}
		}	
	}
	
	class ViewAction extends SetAction {
		public ViewAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}

		public void actionPerformed(ActionEvent e) {
			if (this==gSizeAction[0]) {
				Grid.ableGrid(false);
				myFrame.repaint();
			}
			for (int i=1; i<5; i++) {
				if (this==gSizeAction[i]) {
					Grid.ableGrid(true);
					Grid.changeGridSize(i);
					myFrame.repaint();
				}
			}
			
			if (this==snapAction) {
				Grid.ableSnap(snap.getState());
			}
			
			for (int i=0; i<lafAction.length; i++) {
				if (this==lafAction[i]) {
					try {
						UIManager.setLookAndFeel(laf[i].getClassName());
						SwingUtilities.updateComponentTreeUI(myFrame);
					} catch (Exception exc) {
						System.err.println("Error loading L&F: " + exc);
					}
				}
			}
		}	
	}
	
	class FuncAction extends SetAction {
		private int typeID;
		public FuncAction(String name, int _typeID, String tooltip, String keystroke) {
			super(name, tooltip, keystroke, ' ', true);
			typeID = _typeID;
		}

		public void actionPerformed(ActionEvent e) {
			// Deselect All Actions
			selectAction.setSelected(false);
			arrowAction.setSelected(false);
		    circleAction.setSelected(false);
		    squareAction.setSelected(false);
		    ellipseAction.setSelected(false);
		    rectangleAction.setSelected(false);
		    roundrectangleAction.setSelected(false);
		    triangleAction.setSelected(false);
		    starAction.setSelected(false);
		    sixpolygonAction.setSelected(false);
		    this.setSelected(true);
		    
			
			drawFrame = getCurrentFrame();
			if (drawFrame!=null) {
					
				// 設置目前功能模式
				setMode(typeID);
				drawFrame.getDrawPanel().getSelectionObject().clearSelection();		
				
				// 將所有 DrawFrame 都設為相同的操作模式
				JInternalFrame frames[] = desktop.getAllFrames();
				for (int i=0; i<frames.length; i++) {
					DrawFrame df = (DrawFrame)frames[i];
					df.getStatusBar().changeText(typeID);
					df.ableSelection(typeID==SELECT);
				}
				
				// 如果改變模式即刪除未繪完的箭頭
			      if ((typeID!=ARROW) && (drawFrame.getDrawPanel().arrow!=null)) {
			    	  drawFrame.getDrawPanel().arrow.delete();
			    	  drawFrame.getDrawPanel().arrow = null;
			    	  drawFrame.getDrawPanel().repaint();
			      }
			}
		}	
	}

	class EditAction extends SetAction {
		public EditAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}

		public void actionPerformed(ActionEvent e) {
			if (this==deleteAction) {drawFrame.getDrawPanel().getSelectionObject().deleteSelection();}
		}	
	}
	
	class DatabaseAction extends SetAction {
		public DatabaseAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}

		public void actionPerformed(ActionEvent e) {
			if (this==searchAction) {System.err.println("Search");}
		}	
	}
	
	class HelpAction extends SetAction {
		public HelpAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}

		public void actionPerformed(ActionEvent e) {
			
		}	
	}
	

	public static void main(String[] args) {
		new mainFrame("Pathway Editor");
	}

}
