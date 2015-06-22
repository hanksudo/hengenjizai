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
 * @comment: ���ε���
 * @author Han-Hong Wang
 * @date 2007/3/20
 */
public class JSplitPaneDemo2 extends JFrame {
	public JSplitPaneDemo2() {
		// Button Panel �إߤ@�� Panel ��m���s
		JPanel btnPanel = new JPanel();	
		
		// �إ߫��s
		JButton oneBtn = new JButton("One");
		JButton twoBtn = new JButton("Two");
		
		// �N���s�[�J Button Panel
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
		
		// �NEditor Pane �MTextPane�[�JBottomPanel
		bottomPanel.add(paneScrollPane, BorderLayout.WEST);
		bottomPanel.add(editorScrollPane, BorderLayout.EAST);
		
		// �k�W���� Panel
		JPanel topPanel = new JPanel();
		JLabel label1 = new JLabel("Label one");
		JLabel label2 = new JLabel("Label two");
		topPanel.add(label1);
		topPanel.add(label2);
		
		// �k�䪺�W�USplitPane
		JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		// ��ӵ��������kPane
		JSplitPane leftrightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnPanel, mainPane);
		mainPane.setOneTouchExpandable(true);	// ���b�Ǫ�Pane�T���Y����s
		leftrightPane.setOneTouchExpandable(true);	// ���b�Ǫ�Pane�T���Y����s

		// �N���Χ���Pane�[�JJFrame
		this.getContentPane().add(leftrightPane);
		this.setTitle("This is JSplitPaneDemo2.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();	// ���o�ѪR��
		this.setSize(ScreenSize.width*65/100, ScreenSize.height*45/100);	// �]�m�����j�p
		this.setLocationRelativeTo(null);	// �����m��
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new JSplitPaneDemo2();
	}

}
