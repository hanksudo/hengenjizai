import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @comment: �ϥ�JOptionPane ���T�����
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JOptionPaneMessage {
	public static void main(String[] args) {
		JFrame f = new JFrame("JOptionPane");
		JOptionPane.showMessageDialog(f,"�w��Ө찪���j!!","Welcome",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(f,"�A���w�Y�Ǥ���!?","Question",JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(f,"�{�����~!!","Error",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(f,"�q���Y�N�z��!!","WARNING",JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(f,"TEST!!","Normal",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}
