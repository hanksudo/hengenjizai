import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @comment: �l�����d��, �ݧ⪩���t�m�޲z������
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JInternalFrameDemo {
	private static JPopupMenu titlePopup;

	public static void main(String[] args) {
		JFrame f = new JFrame("JInternalFrame");
		// JInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) 
		JInternalFrame cf = new JInternalFrame("This is internal window.", true, true, true, true);

		
		// �N�l�����[�JJFrame
		f.getContentPane().add(cf);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���������t�m�޲z��
		f.getContentPane().setLayout(null);
		
		cf.setSize(200, 200);
		f.setSize(800, 600);
		
		cf.setVisible(true);	// ��ܤl����
		f.setVisible(true);
	}
}
