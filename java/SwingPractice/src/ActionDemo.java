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
		
		// �إߥ\���C�õ����׭�����u
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		// �إ߿��C�å[��\���C
		JMenu menu = new JMenu("Main");
		menuBar.add(menu);
		
		// �إߤu��C�õ���etched border
		toolBar = new JToolBar();
		toolBar.setBorder(new EtchedBorder());
		
		URL imageURL = this.getClass().getResource("middle.gif");
		SampleAction openAction = new SampleAction("Open", new ImageIcon(imageURL));
		
		// �s�W�\�ඵ�ئܥ\���
		JMenuItem openMItem = new JMenuItem(openAction);
		menu.add(openMItem);
		
		// �s�W���s��ToolBar
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
