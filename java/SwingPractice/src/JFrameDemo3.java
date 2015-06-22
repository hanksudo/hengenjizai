import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


/**
 * @comment: JFrame ½d¨Ò, ¨Ï¥ÎWindowAdapter
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JFrameDemo3 {
	public static void main(String[] args) {
		JFrame f = new JFrame("JFrameDemo3");
		f.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);
		
		f.setSize(400, 300);
		f.setVisible(true);
	}

}
