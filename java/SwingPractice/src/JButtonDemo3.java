import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * @comment: JButton Demo 放入Panel並加入熱鍵, 並用GridLayput排版, 使用AbstractButton改變按鈕
 * @author Han-Hong Wang
 * @date 2007/3/14
 */
public class JButtonDemo3 extends JFrame {
	private JPanel buttonPanel = new JPanel();
	
	public JButtonDemo3() {
		buttonPanel.setLayout(new GridLayout(1, 4, 4, 4));
		
		btnAction sportAction = new btnAction("Sports", "middle.gif", KeyEvent.VK_S, "This is Sports");
		btnAction movieAction = new btnAction("Movies", "middle.gif", KeyEvent.VK_M, "This is Movies");
		btnAction travelAction = new btnAction("Travel", "middle.gif", KeyEvent.VK_T, "This is Travels");
		btnAction artAction = new btnAction("Art", "middle.gif", KeyEvent.VK_A, "This is Arts");
		buttonPanel.add(new JButton(sportAction));
		buttonPanel.add(new JButton(movieAction));
		buttonPanel.add(new JButton(travelAction));
		buttonPanel.add(new JButton(artAction));
		
		this.getContentPane().add(buttonPanel);
		this.pack();
		this.setVisible(true);
	}
	
	class btnAction extends AbstractAction {
		public btnAction(String text, String imgPath, int mnemonic, String tooltip) {
			super(text);
			
			// 設置圖片
			URL imageURL = this.getClass().getResource(imgPath);
				
		    if (imageURL == null) {
		    	System.err.println("Resource not found: " + imgPath);
		    } else {
		    	putValue(SMALL_ICON, new ImageIcon(imageURL));
		    }
			
		    // 設置 tooltip
		    if(tooltip != null) { putValue(SHORT_DESCRIPTION, tooltip); }
		    
		    // 設置熱鍵
		    putValue(MNEMONIC_KEY, new Integer(mnemonic));
		}
		
		public void actionPerformed(ActionEvent e) {
			System.err.println(e.getActionCommand());
		}
	}

	
	
	public static void main(String[] args) {
		new JButtonDemo3();
	}
}
