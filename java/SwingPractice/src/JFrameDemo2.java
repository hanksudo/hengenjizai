import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @comment: JFrame 範例 實作WindowListener介面
 * @author Han-Hong Wang
 * @date 2007/5/2
 */

public class JFrameDemo2 implements WindowListener {

	public static void main(String[] args) {
		JFrame f = new JFrame("JFrameDemo2");
		
		// 取得系統目前解析度
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		f.setSize(400, 300);
		// 將視窗置中
		f.setLocation((screen.width-400)/2, (screen.height-300)/2);
		f.setVisible(true);
		
		JFrameDemo2 g = new JFrameDemo2();
		f.addWindowListener(g);	// 加入視窗傾聽器
	}

	public void windowActivated(WindowEvent arg0) {
		System.out.println("Frame Activated.");
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
		System.out.println("Deiconified");
	}

	public void windowIconified(WindowEvent e) {
		System.out.println("視窗最小化");
	}

	public void windowOpened(WindowEvent e) {
	}

}
