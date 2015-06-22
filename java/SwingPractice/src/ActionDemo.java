import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 * @comment: AbstractAction Demo
 * @author Han-Hong Wang
 * @date 2007/3/14
 */
public class ActionDemo extends JPanel {
	public JMenuBar menuBar;
	public JToolBar toolBar;
	
	public ActionDemo() {
		super(true);
		
		// 建立功能表列並給它斜面的邊線
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		// 建立選單列並加到功能表列
		JMenu menu = new JMenu("Main");
		menuBar.add(menu);
		
		// 建立工具列並給它etched border
		toolBar = new JToolBar();
		toolBar.setBorder(new EtchedBorder());
		
		URL imageURL = this.getClass().getResource("middle.gif");
		SampleAction openAction = new SampleAction("Open", new ImageIcon(imageURL));
		
		// 新增功能項目至功能表
		JMenuItem openMItem = new JMenuItem(openAction);
		menu.add(openMItem);
		
		// 新增按鈕至ToolBar
		JButton openBtn = new JButton(openAction);
		toolBar.add(openBtn);		
	}
	
	class SampleAction extends AbstractAction {
		public SampleAction(String text, Icon icon) {
			super(text, icon);
		}
		
		public void actionPerformed(ActionEvent e) {
			System.err.println("Action [ "+e.getActionCommand()+" ]performed!");
		}
		
	}
	
	public static void main(String[] args) {
		ActionDemo demo = new ActionDemo();
		JFrame frame = new JFrame("Action Example");
		frame.setJMenuBar(demo.menuBar);
		frame.getContentPane().add(demo.toolBar);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

}
