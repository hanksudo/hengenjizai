import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @comment: 建立三個Checkbox並改變選取圖案
 * @author Han-Hong Wang
 * @date 2007/3/22
 */
public class JCheckBoxDemo extends JFrame implements ItemListener {

	static JCheckBox jcb1;
	static JCheckBox jcb2;
	static JCheckBox jcb3;
	static JLabel labSelected;
	
	public JCheckBoxDemo() {
		JPanel topPanel = new JPanel();
		JPanel boxPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
//		topPanel.setSize(300, 150);
		
		// 建立 3個CheckBox
		jcb1 = new JCheckBox("公車(B)");
		jcb2 = new JCheckBox("火車(T)");
		jcb3 = new JCheckBox("汽車(C)");
		
		// 建立快捷鍵
		jcb1.setMnemonic('B');
		jcb2.setMnemonic('T');
		jcb3.setMnemonic('C');
		
		// 將Checkbox 加入topPanel
		boxPanel.add(jcb1);
		boxPanel.add(jcb2);
		boxPanel.add(jcb3);
		
		// Checkbox加上ItemListener
		jcb1.addItemListener(this);
		jcb2.addItemListener(this);
		jcb3.addItemListener(this);

		// 用來顯示被選取的Box
		labSelected = new JLabel();
		labSelected.setText("您選取了：");
		
		// 將checkbox預設為選取
		jcb1.setSelected(true);
		jcb2.setSelected(true);
		jcb3.setSelected(true);
		
		topPanel.add(boxPanel, BorderLayout.PAGE_START);
		topPanel.add(labSelected, BorderLayout.CENTER);
		
		setTitle("JCheckBoxDemo");
		getContentPane().add(topPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
		setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent ie) {
		// true為打勾 false為icon
		JCheckBox iejcb = (JCheckBox)ie.getSource();
		if (ie.getStateChange() == 2) {
			iejcb.setIcon(getImage("./images/middle.gif"));
		} else {
			iejcb.setIcon(null);
		}
		
		String strTmp="您選取了：";
		
		if (jcb1.isSelected()) {
			strTmp+="公車、";
		}
		
		if (jcb2.isSelected()) {
			strTmp+="火車、";
		}
		
		if (jcb3.isSelected()) {
			strTmp+="汽車、";
		}
		
		StringBuffer temp = new StringBuffer(strTmp);
		temp.deleteCharAt(temp.length()-1);
		labSelected.setText(temp.toString());
	}

	public ImageIcon getImage(String imgPath) {
		URL imageURL = this.getClass().getResource(imgPath);
		return new ImageIcon(imageURL);
	}
	
	public static void main(String[] args) {
		new JCheckBoxDemo();
	}
}
