import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @comment:��r�s�边 Beta1
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/3
 */
public class MainEditor {
	private JFrame myFrame;
	private boolean changed = false;
	private JFileChooser fileChooser;
	private JTextArea txtArea;
	private File currentDocument=null;
	
	public MainEditor() {
		myFrame = new JFrame("Untitled 1");
		myFrame.setSize(400, 400);
		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (checkSave()) {myFrame.dispose();};
			}
		});
		
		txtArea = new JTextArea();
		txtArea.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {}

			// ��������O�_�Q�ק�
			public void insertUpdate(DocumentEvent arg0) {
				if (!changed) {
					changed = true;
					setNewTitle("*"+myFrame.getTitle());
				}
			}

			public void removeUpdate(DocumentEvent arg0) {}
			
		});
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new JavaFilter("txt","��r���(*.txt)"));
		
		myFrame.add(txtArea);
		myFrame.setVisible(true);
	}
	
	public void setNewTitle(String title) {
		myFrame.setTitle(title);
	}
	
	// �ˬd�O�_�s��
	public boolean checkSave() {
		if (changed) {
			int result=JOptionPane.showConfirmDialog(myFrame, "Current file has changed. Save current file?",
					" Confirm Save Current File",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE);
			switch(result) {
				case JOptionPane.YES_OPTION: return saveFile(currentDocument);
				case JOptionPane.NO_OPTION: break;
				case JOptionPane.CANCEL_OPTION: return false;
			}
		}
		return true;
	}
	
	// �x�s�ɮ�
	public boolean saveFile(File f) {
		if (f!=null && f.exists()) {
			try {
				BufferedWriter buf = new BufferedWriter(new FileWriter(f));
				buf.write(txtArea.getText());
				buf.close();
				System.err.println("Save to "+f.getName()+" successful.");
			} catch (Exception e) {
				System.err.println("Save file error!");
			}
		} else {
			return saveFileAs();
		}

		return true;
	}
	
	// �t�s�s��
	public boolean saveFileAs() {
		int result =  fileChooser.showSaveDialog(myFrame);
		
		if (result==JFileChooser.CANCEL_OPTION) {
			return false;
		} else {
			File f = fileChooser.getSelectedFile();
			
			// �P�_�O�_�[�w�����ɦW
			int i = f.getName().lastIndexOf('.');
			if (!f.getName().substring(i+1).equals(".txt")) {
				File newFile = new File(f.toString()+".txt");
				f = newFile;
			}
			
			try {
				f.createNewFile();
			} catch (Exception e) {
				System.err.println("file save as error.");
			}
			saveFile(f);
		}
		return true;
	}

	public static void main(String[] args) {
		new MainEditor();
	}
	
	class JavaFilter extends javax.swing.filechooser.FileFilter {
		String extension;	// ���ɦW
		String description;	// �ɮ״y�z
		
		public JavaFilter(String _ext, String _des) {
			extension = _ext.toLowerCase();
			description = _des;
		}

		public boolean accept(File arg0) {
			if (arg0.isDirectory()) {
				return true;
			}
			
			String ext = null;
			String s = arg0.getName();	// ���o�ɮצW��
			int i = s.lastIndexOf('.');
			
			if (i>0 && i<s.length()-1) {
				ext = s.substring(i+1).toLowerCase();
				if (extension.equals(ext)) {return true;}
			}
			return false;
		}

		public String getDescription() {
			return description;
		}
	}

}
