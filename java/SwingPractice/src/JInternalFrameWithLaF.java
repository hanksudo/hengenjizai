import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 * @comment: ���ܤl�����������˦�
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JInternalFrameWithLaF {
	public static void main(String[] args) {
		JFrame f = new JFrame("JInternalFrameWithLaF");
		JInternalFrame cf = new JInternalFrame("�o�O�l����",true,true,true,true);
		
		f.getContentPane().setLayout(null);
		f.getContentPane().add(cf);
		
		// �]�w�l�����˦�
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(cf);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setSize(300,100);
		f.setSize(600, 400);
		cf.setVisible(true);
		f.setVisible(true);
	}
}
