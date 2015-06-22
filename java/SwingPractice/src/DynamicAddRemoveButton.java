import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @comment: 動態產生移除JButton 並監控索引值
 * @author Han-Hong Wang
 * @date 2007/5/11
 */

public class DynamicAddRemoveButton implements ActionListener {
	ArrayList btnArr = new ArrayList();
	JFrame f;
	newButton btn;
	btnData btnContent;
	
	public DynamicAddRemoveButton() {
		f = new JFrame("Dynamic Add Remove Button");
		JButton addBtn = new JButton("Add One Button");
		addBtn.setMnemonic('A');
		addBtn.addActionListener(this);
		
		f.getContentPane().add(addBtn);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
	}

	private static class btnData {
		public newButton btn;
	}
	
	public static void main(String[] args) {
		new DynamicAddRemoveButton();
	}

	public void actionPerformed(ActionEvent e) {
		int freeSpace=getFreeObject();
		System.err.println(freeSpace);
		btn = setObjectIndex(freeSpace);
		
		f.add(btn);
		f.validate();	// 驗證動態即時產生
	}
	
	public newButton setObjectIndex(int index) {
	    if(index<0) { return null; }	    
	    btnContent=(btnData)(btnArr.get(index));	    
	    if(btnContent.btn==null) { btnContent.btn=new newButton("Button "+index, index); }
	    return btnContent.btn;
	}
	
	public int getFreeObject() {
		int freeSpace = -1;
		
		for (int i=0; i<btnArr.size(); i++) {
			btnContent = (btnData)(btnArr.get(i));
			if (btnContent.btn==null) {
				freeSpace = i;break;
			}
		}
		
		if (freeSpace == -1) {
			btnArr.add(new btnData());
			freeSpace = btnArr.size()-1;
		}
		
		return freeSpace;
	}

	public void removeButton(int index) {
		btnContent=(btnData)(btnArr.get(index));
		f.remove(btnContent.btn);	// 從Frame中移除	
		btnContent.btn=(newButton)null;	// 從物件陣列移除
		
		f.validate();	// 驗證動態即時產生
		f.repaint();	// 重繪Frame
	}
	
	public class newButton extends JButton implements ActionListener {
		private int index;
		newButton(String caption, int _index) {
			super(caption);
			index = _index;
			this.addActionListener(this);
		}
		
		public int getIndex() {
			return index;
		}

		public void actionPerformed(ActionEvent e) {
			removeButton(index);
		}
	}
}
