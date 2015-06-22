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
 * @comment: ���ΤT��Pane, �W�����s �k���Editor �����TextPane
 * @author Han-Hong Wang
 * @date 2007/3/19
 */
public class JSplitPaneDemo extends JFrame implements ActionListener {
	
	private static JEditorPane editorPane;
	private static JTextPane textPane;
	
	public JSplitPaneDemo() {
		// Button Panel
		JPanel btnPanel = new JPanel();	//�إߤ@�� Panel ��m���s
		btnPanel.setLayout(new GridLayout(1,3));	// Button Panel �ƪ�
		
		// �إ߫��s
		JButton clrTextBtn = new JButton("Clear TextPane");
		JButton noBtn = new JButton("Nothing");
		JButton clearEditBtn = new JButton("Clear Editor");

		// Button�[�J ActionListener
		clrTextBtn.addActionListener(this);
		noBtn.addActionListener(this);
		clearEditBtn.addActionListener(this);
		
		// �N���s�[�J Button Panel
		btnPanel.add(clrTextBtn);
		btnPanel.add(noBtn);
		btnPanel.add(clearEditBtn);
		
		// �إ� Bottom Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		// -- Text Pane on Left
		textPane = new JTextPane();
		JScrollPane paneScrollPane = new JScrollPane(textPane);	// ���b
		paneScrollPane.setBorder(BorderFactory.createCompoundBorder(	// �~��
                BorderFactory.createTitledBorder("TextPane Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		paneScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paneScrollPane.setPreferredSize(new Dimension(300, 250));	// �վ�j�p
		

		// -- Create Editor Pane on Right
		editorPane = new JEditorPane();
		JScrollPane editorScrollPane = new JScrollPane(editorPane);	// ���b
		editorScrollPane.setBorder(BorderFactory.createCompoundBorder(	// �~��
                BorderFactory.createTitledBorder("Editor Fields"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		editorScrollPane.setPreferredSize(new Dimension(300, 250));	// �վ�j�p

		// �NText Pane �M  Editor Pane �[�JBottom Panel�ñƪ�
		bottomPanel.add(paneScrollPane, BorderLayout.WEST);	
		bottomPanel.add(editorScrollPane, BorderLayout.EAST);
		
		// �إߤ@�ӤW�U���Ϊ� JSplitPane �é�J�W�UPanel
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, btnPanel, bottomPanel);
		splitPane.setOneTouchExpandable(true); // ���b�Ǫ�Pane�T���Y����s
		
		// �NsplitPane�[�JJFrame
		this.getContentPane().add(splitPane);
		this.setTitle("This is JSplitPaneDemo.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();	// ���o�ѪR��
		this.setSize(ScreenSize.width*50/100, ScreenSize.height*50/100);	// �]�m�����j�p
		this.setLocationRelativeTo(null);	// �����m��
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// ���U���׮ɲM����r
		if (e.getActionCommand()=="Clear Editor") {editorPane.setText(null);}
		if (e.getActionCommand()=="Clear TextPane") {textPane.setText(null);}
	}
	
	public static void main(String[] args) {
		new JSplitPaneDemo();
	}
}
