import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

/**
 * @comment: 分割三個Pane, 上方放按鈕 右邊放Editor 左邊放TextPane
 * @author Han-Hong Wang
 * @date 2007/3/19
 */
public class JSplitPaneDemo extends JFrame implements ActionListener {
	
	private static JEditorPane editorPane;
	private static JTextPane textPane;
	
	public JSplitPaneDemo() {
		// Button Panel
		JPanel btnPanel = new JPanel();	//建立一個 Panel 放置按鈕
		btnPanel.setLayout(new GridLayout(1,3));	// Button Panel 排版
		
		// 建立按鈕
		JButton clrTextBtn = new JButton("Clear TextPane");
		JButton noBtn = new JButton("Nothing");
		JButton clearEditBtn = new JButton("Clear Editor");

		// Button加入 ActionListener
		clrTextBtn.addActionListener(this);
		noBtn.addActionListener(this);
		clearEditBtn.addActionListener(this);
		
		// 將按鈕加入 Button Panel
		btnPanel.add(clrTextBtn);
		btnPanel.add(noBtn);
		btnPanel.add(clearEditBtn);
		
		// 建立 Bottom Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		// -- Text Pane on Left
		textPane = new JTextPane();
		JScrollPane paneScrollPane = new JScrollPane(textPane);	// 捲軸
		paneScrollPane.setBorder(BorderFactory.createCompoundBorder(	// 外框
                BorderFactory.createTitledBorder("TextPane Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		paneScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paneScrollPane.setPreferredSize(new Dimension(300, 250));	// 調整大小
		

		// -- Create Editor Pane on Right
		editorPane = new JEditorPane();
		JScrollPane editorScrollPane = new JScrollPane(editorPane);	// 捲軸
		editorScrollPane.setBorder(BorderFactory.createCompoundBorder(	// 外框
                BorderFactory.createTitledBorder("Editor Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		editorScrollPane.setPreferredSize(new Dimension(300, 250));	// 調整大小

		// 將Text Pane 和  Editor Pane 加入Bottom Panel並排版
		bottomPanel.add(paneScrollPane, BorderLayout.WEST);	
		bottomPanel.add(editorScrollPane, BorderLayout.EAST);
		
		// 建立一個上下分割的 JSplitPane 並放入上下Panel
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, btnPanel, bottomPanel);
		splitPane.setOneTouchExpandable(true); // 捲軸旁的Pane三角縮放按鈕
		
		// 將splitPane加入JFrame
		this.getContentPane().add(splitPane);
		this.setTitle("This is JSplitPaneDemo.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();	// 取得解析度
		this.setSize(ScreenSize.width*50/100, ScreenSize.height*50/100);	// 設置視窗大小
		this.setLocationRelativeTo(null);	// 視窗置中
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// 按下按忸時清除文字
		if (e.getActionCommand()=="Clear Editor") {editorPane.setText(null);}
		if (e.getActionCommand()=="Clear TextPane") {textPane.setText(null);}
	}
	
	public static void main(String[] args) {
		new JSplitPaneDemo();
	}
}
