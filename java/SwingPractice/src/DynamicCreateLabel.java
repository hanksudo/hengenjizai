import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @comment: 動態產生Label, 並用vaildate驗證
 * @author Han-Hong Wang
 * @date 2007/5/9
 */
public class DynamicCreateLabel {
	private int n=0;
	JFrame f;
	
	public DynamicCreateLabel() {
		f = new JFrame("Dynamic Create Label");
		JButton addBtn = new JButton("Click me to add one label.");
		addBtn.setMnemonic('A');
		addBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mev) {
				JLabel label = new JLabel("Label "+n++);
				label.setBounds(100*n, 25*n, 100, 25);
				f.add(label);
				f.validate();	// 驗證動態即時產生
			}
		});

		f.getContentPane().add(addBtn);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DynamicCreateLabel();
	}

}
