import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * @comment: JDesktopPane ฝdจา
 * @author Han-Hong Wang
 * @date 2007/5/6
 */
public class JDesktopPaneDemo {

	public JDesktopPaneDemo() {
		JFrame f = new JFrame();
		JDesktopPane dpPane = new JDesktopPane();
		f.getContentPane().add(dpPane);
		
		JInternalFrame jif = new JInternalFrame(null, true, true, true, true);
		dpPane.add(jif);
		jif.setSize(400, 300);
		jif.setVisible(true);

		System.err.println(dpPane.getAllFrames().length);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new JDesktopPaneDemo();
	}

}
