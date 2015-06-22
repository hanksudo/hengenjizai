import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @comment: �ϥ�JFileChooser�}�ҹϤ�
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JFileChooserWithImage {
	public static void main(String[] args) {
		JFrame f = new JFrame("JFileChooseWithImage");
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(f)==JFileChooser.APPROVE_OPTION) {	// �}������
			// �}�ҹϤ�
			ImageIcon img = new ImageIcon(fc.getSelectedFile().getPath());
			JLabel labImg = new JLabel(img);
			labImg.setBounds(0, 0, 1024, 768);
			f.getContentPane().add(labImg);	
		}
		
		f.getContentPane().setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);	// �̤j��
		f.setVisible(true);
	}

}

