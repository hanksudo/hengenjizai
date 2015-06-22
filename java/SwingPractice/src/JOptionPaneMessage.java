import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @comment: 使用JOptionPane 做訊息方塊
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class JOptionPaneMessage {
	public static void main(String[] args) {
		JFrame f = new JFrame("JOptionPane");
		JOptionPane.showMessageDialog(f,"歡迎來到高應大!!","Welcome",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(f,"你喜歡吃些什麼!?","Question",JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(f,"程式錯誤!!","Error",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(f,"電腦即將爆炸!!","WARNING",JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(f,"TEST!!","Normal",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}
