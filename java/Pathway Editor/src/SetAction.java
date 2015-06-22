

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;


abstract class SetAction extends AbstractAction {
	
	public SetAction(String name, String tooltip, String keystroke) {
		super(name);
		
		// 設置圖片
		URL imageURL = ClassLoader.class.getResource("/Images/"+name+".png");
		
        if (imageURL == null) {
            System.err.println("Resource not found: " + name + ".png");
        } else {
        	putValue(SMALL_ICON, new ImageIcon(imageURL));
        }
		
		// 設置工作提示
		if(tooltip != null) { putValue(SHORT_DESCRIPTION, tooltip); }
		
		// 設置快捷鍵
		if(keystroke != null) { putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(keystroke)); }
	}
	
	public SetAction(String name, String tooltip, String keystroke, char mnemonic) {
		this(name, tooltip, keystroke);
		// 設置助憶鍵
		putValue(MNEMONIC_KEY, new Integer(mnemonic));
	}
	
	public SetAction(String name, String tooltip, String keystroke, char mnemonic, boolean toggleable) { 
		this(name, tooltip, keystroke, mnemonic);
		putValue("selected", new Boolean(false));
	}
	
	public void setSelected(boolean selected) {
		Boolean b = (Boolean)getValue("selected");
		if (b != null) {
			putValue("selected", null);
			putValue("selected", new Boolean(selected));
		}
	}
}
