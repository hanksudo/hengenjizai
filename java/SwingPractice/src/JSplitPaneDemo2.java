import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

/**
 * @comment: 分割視窗
 * @author Han-Hong Wang
 * @date 2007/3/20
 */
public class JSplitPaneDemo2 extends JFrame {
	public JSplitPaneDemo2() {
		// Button Panel 建立一個 Panel 放置按鈕
		JPanel btnPanel = new JPanel();	
		
		// 建立按鈕
		JButton oneBtn = new JButton("One");
		JButton twoBtn = new JButton("Two");
		
		// 將按鈕加入 Button Panel
		btnPanel.add(oneBtn);
		btnPanel.add(twoBtn);
		
		// Bottom Panel
		JPanel bottomPanel = new JPanel();
		
		// -- Text Pane on Left of Bottom Panel
		JTextPane textPane = new JTextPane();
		JScrollPane paneScrollPane = new JScrollPane(textPane);
		paneScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("TextPane Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		paneScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paneScrollPane.setPreferredSize(new Dimension(250, 250));
		

		// -- Editor Pane on Right of Bottom Panel 
		JEditorPane editorPane = new JEditorPane();
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Editor Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		editorScrollPane.setPreferredSize(new Dimension(250, 250));
		
		// 將Editor Pane 和TextPane加入BottomPanel
		bottomPanel.add(paneScrollPane, BorderLayout.WEST);
		bottomPanel.add(editorScrollPane, BorderLayout.EAST);
		
		// 右上角的 Panel
		JPanel topPanel = new JPanel();
		JLabel label1 = new JLabel("Label one");
		JLabel label2 = new JLabel("Label two");
		topPanel.add(label1);
		topPanel.add(label2);
		
		// 右邊的上下SplitPane
		JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		// 整個視窗的左右Pane
		JSplitPane leftrightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnPanel, mainPane);
		mainPane.setOneTouchExpandable(true);	// 捲軸旁的Pane三角縮放按鈕
		leftrightPane.setOneTouchExpandable(true);	// 捲軸旁的Pane三角縮放按鈕

		// 將分割完的Pane加入JFrame
		this.getContentPane().add(leftrightPane);
		this.setTitle("This is JSplitPaneDemo2.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();	// 取得解析度
		this.setSize(ScreenSize.width*65/100, ScreenSize.height*45/100);	// 設置視窗大小
		this.setLocationRelativeTo(null);	// 視窗置中
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new JSplitPaneDemo2();
	}

}
