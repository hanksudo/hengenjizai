import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @comment: JButton ¨Æ¥ó°»´ú
 * @author Han-Hong Wang
 * @date 2007/3/13
 */
public class JButtonEvents {

	public static void main(String[] args) {
		JButton btn = new JButton("press me");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionEvent!");
			}
		});
		
		btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("ItemEvent!");
			}
		});
		
		btn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("ChangeEvent!");
			}
		});
		
		JFrame f = new JFrame();
		f.getContentPane().add(btn);
		f.pack();
		f.setVisible(true);
	}

}
