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
 * @comment: �D�e�����
 * @author Han-Hong Wang
 * @date 2007/5/5
 */

public class mainFrame implements Constants {
	private JFrame myFrame;
	private DrawFrame drawFrame;
	private JDesktopPane desktop;
	private JCheckBoxMenuItem snap;
	
	private static ArrayList frameIndex = new ArrayList();	// �����������ޭ�
	private static frameData frameContent;	// �����������e�ʱ�
	
	private static int Mode = SELECT; // �w�]�\��

	private FileAction newAction, openAction, closeAction, closeallAction, saveAction, saveallAction, saveasAction, exitAction;
	private ViewAction snapAction;
	private FuncAction selectAction, arrowAction, circleAction, ellipseAction, squareAction, rectangleAction, roundrectangleAction,
					   triangleAction, starAction, sixpolygonAction;
	private EditAction cutAction, copyAction, pasteAction, deleteAction;
	private DatabaseAction searchAction;
	private HelpAction aboutAction;
	
	UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();	// Ū���Ҧ��w�˪�Look and Feel
	ViewAction lafAction[] = new ViewAction[laf.length];
	ViewAction gSizeAction[] = new ViewAction[5];
	
	public mainFrame(String frameTitle) {
		myFrame = new JFrame(frameTitle);
		
		// �ϥΥثe�t�Υ~�[ Get System LookAndFeel
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(laf[DEFAULT_LaF].getClassName());	// �w�]Theme
		} catch (Exception exc) {
			System.err.println("Error loading L&F: " + exc);
		}
		
		// ���o�t�Υثe�ѪR��
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// �]�m�����j�p
		Dimension frameSize = new Dimension(1024, 768);
		myFrame.setSize(frameSize);
		
		// �N�����m��
		myFrame.setLocation((int)(screen.width-frameSize.getWidth())/2, (int)(screen.height-frameSize.getHeight())/2);
		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // �w�]�����Ҧ�
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitAction.actionPerformed(null);
			}
		});
		
		// �]�m JFrame Icon
		//URL imageURL = ClassLoader.class.getResource("/Images/icon.png");
		//f.setIconImage(new ImageIcon(imageURL).getImage());		
		
		desktop = new JDesktopPane();
		// �]�mDesktopPane�즲�Ҧ�
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		desktop.setBackground(Color.black);

		// �NDesktopPane��JFrame
		myFrame.getContentPane().add(desktop);
		
		// �]�mMenuBar
		myFrame.setJMenuBar(createMenuBar());
		
		// �]�mToolBar
		myFrame.getContentPane().add(ToolBar_General(), BorderLayout.NORTH);
		myFrame.getContentPane().add(ToolBar_DrawObject(), BorderLayout.WEST);
		
		// �w�]��u�j�p
		Grid.changeGridSize(DEFAULT_GRID_SIZE);
		
		// �Ұ� JFrame
		myFrame.setVisible(true);
		
		// �إ߷s��
		createNewFile();
	}
	
	/** Create Menu Bar **/
	public JMenuBar createMenuBar() {
		// �إ� JMenuBar
		JMenuBar myMenu = new JMenuBar();
		
		// �إ�JMenu
        String Menus[] = {"File","Edit","View","Function","Database","Help"};	//���W��
        char Mnemonics[] = {'F','E','V','U','D','H'};	// �ֱ���
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
    	
    		// �Nsub Menu �[�JView Menu	
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
		myToolBar.setFloatable(false);	// �T��ToolBar�B��

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
		
		
		// �T����s���o�J�I
		for(int i=0; i<myToolBar.getComponentCount(); i++) {
			myToolBar.getComponent(i).setFocusable(false);
		}

		return myToolBar;
	}
	
	public JToolBar ToolBar_DrawObject() {
		JToolBar myToolBar = new JToolBar();
		myToolBar.setFloatable(true);	// �]�mToolBar�i�B��(True/False)

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

		
		// �T����s���o�J�I
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
			this.setText("");	// �N��r����
			a.addPropertyChangeListener(this);
		}
		
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getPropertyName()=="selected") {
				Boolean b=(Boolean)evt.getNewValue();
				if (b!=null) { setSelected(b.booleanValue()); }
			}
		}
	}
	
	
	// �s�W���� - �}�s�ɮ�
	public void createNewFile() {
		// ���o�ثe������
		int freeSpace = getFreeSpace();
		
		// ���o�������
		drawFrame = setObjectsIndex(freeSpace);

		// �s�W�ɮ׮ɰ�����m
		drawFrame.setLocation(30*(freeSpace%10)+30*(freeSpace/10), 30*(freeSpace%10));
				
		// �N���������[�JDesktopPane�޲z
		desktop.add(drawFrame);
		
		// ���o�s�ɮ׵J�I
		try {
			drawFrame.setSelected(true);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	/** Frame ������� �ʱ� **/
	// �w�q�ثe frame�����e
	private static class frameData {
		public DrawFrame drawFrame;
	}
	
	// ���o�Ū�������� ���Шϥ�
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
	
	// �]�mDrawFrame������� 
	public DrawFrame setObjectsIndex(int index) {
	    if(index<0) { return null; }	    
	    frameContent=(frameData)(frameIndex.get(index));	    
	    if(frameContent.drawFrame==null) { frameContent.drawFrame=new DrawFrame("BioPathWay "+(index+1)); }
	    return frameContent.drawFrame;
	}
	
	// �ˬd�Ҧ��w�}�Ҫ��ɮ׬O�_�s��
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
	
	// ���o�ثe�����Frame	
	public DrawFrame getCurrentFrame() {
		return (DrawFrame)desktop.getSelectedFrame();
	}
	
	/** �\��Ҧ� **/
	// �]�m�ثe�\��Ҧ�
	public void setMode(int m) {
		Mode = m;
	}
	
	// ���o�ثe�\��Ҧ�
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
					
				// �]�m�ثe�\��Ҧ�
				setMode(typeID);
				drawFrame.getDrawPanel().getSelectionObject().clearSelection();		
				
				// �N�Ҧ� DrawFrame ���]���ۦP���ާ@�Ҧ�
				JInternalFrame frames[] = desktop.getAllFrames();
				for (int i=0; i<frames.length; i++) {
					DrawFrame df = (DrawFrame)frames[i];
					df.getStatusBar().changeText(typeID);
					df.ableSelection(typeID==SELECT);
				}
				
				// �p�G���ܼҦ��Y�R����ø�����b�Y
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
