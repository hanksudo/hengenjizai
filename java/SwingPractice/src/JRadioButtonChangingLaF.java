import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @comment: 用RadioButton改變Look and Feel
 * @author Han-Hong Wang
 * @date 2007/3/22
 */
public class JRadioButtonChangingLaF extends JFrame {

	public JRadioButtonChangingLaF() {
		JPanel myPanel = new JPanel();
		ButtonGroup radioGroup = new ButtonGroup();	//  將RadioButton群組化, 防止多重選取
		
		installGTK();	// 測試安裝GTK介面
		UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();	// 讀取所有安裝的Look and Feel
		myPanel.setLayout(new GridLayout(laf.length, 1));
		for (int i = 0; i < laf.length; i++) {
			JRadioButton radioBtn = new LaFButton(laf[i]);
			radioGroup.add(radioBtn);
			myPanel.add(radioBtn);
		}
		
		getContentPane().add(myPanel, BorderLayout.SOUTH);
		getContentPane().add(new JFileChooser(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}


	// 測試使用GTK
	private void installGTK() {
		try {
			String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			UIManager.setLookAndFeel(GTK);
			UIManager.installLookAndFeel("GTK", GTK);
		} catch (Exception e) {
			System.err.println("Could not install GTK");
		}
	}

	private class LaFButton extends JRadioButton implements ActionListener {
		LaFButton(UIManager.LookAndFeelInfo laf) {
			super(laf.getClassName());
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event) {
			try {
				UIManager.setLookAndFeel(this.getText());
				SwingUtilities.updateComponentTreeUI(JRadioButtonChangingLaF.this);
				// call myFrame.pack()
				// to resize frame for laf
			} catch (IllegalAccessException e) {
				// insert code to handle this exception
			} catch (UnsupportedLookAndFeelException e) {
				// insert code to handle this exception
			} catch (InstantiationException e) {
				// insert code to handle this exception
			} catch (ClassNotFoundException e) {
				// insert code to handle this exception
			}
		}
	}
	
	public static void main(String[] args) {
		new JRadioButtonChangingLaF();
	}
}