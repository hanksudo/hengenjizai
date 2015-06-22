import java.awt.BorderLayout;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


public class DrawFrame extends JInternalFrame implements InternalFrameListener, Constants {
	private DrawPanel drawPanel;
	private StatusBar statusbar;
	private String frameTitle;
	private JavaFilter fileFilter = new JavaFilter(XML_FILETYPE);
	
	public boolean changed = false;
	public File currentFile = null;
	private File prevFile = null;
	
	public DrawFrame(String title) {
		super(title, true, true, true, true);
		
		frameTitle = title;
		drawPanel = new DrawPanel(this);
		
		// ���o�s����N�e����J���bPane
		JScrollPane scroller = new JScrollPane(drawPanel);
		drawPanel.updatePreferredSize();
		
		// �[�J���bPane
		this.getContentPane().add(scroller);
		
		this.setSize(600, 400);
		
		// �]�mStatusBar
		statusbar = new StatusBar();
		
		this.getContentPane().add(statusbar, BorderLayout.PAGE_END);
	
		this.addInternalFrameListener(this);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE); // �w�]�����Ҧ�
		this.setVisible(true);
		
		// �]�m�s�}�ɮת��ثe�\��
		this.getStatusBar().changeText(mainFrame.getMode());
		this.ableSelection(mainFrame.getMode()==SELECT);
	}
	
	// �]�w���e�ܧ�P�_�ç��ܼ��D
	public void setChanged(boolean state) {
		changed = state;
		setNewTitle("* "+frameTitle);
	}
	
	// ������InternalFrame
	public void closeThisFrame() {
		setPrevFrameFocus();
		this.dispose();
	}
	
	// �]�m�W�@�hFrame���o�J�I
	public void setPrevFrameFocus() {
		JDesktopPane dp = (JDesktopPane)this.getParent();
		JInternalFrame frames[] = dp.getAllFrames();
		if (frames.length>1) {
			try {
				frames[1].setSelected(true);
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	// �ˬd�O�_�w�s��
	public boolean checkSave() {
		if (changed) {
			int result=JOptionPane.showConfirmDialog(this,"Current file "+ frameTitle +" has changed. Save current file?",
					" Confirm Save Current File",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE);
			switch(result) {
				case JOptionPane.YES_OPTION: return saveFile();
				case JOptionPane.NO_OPTION: break;
				case JOptionPane.CANCEL_OPTION: return false;
			}
		}
		closeThisFrame();
		return true;
	}
	
//	 �x�s�ɮ�
	public boolean saveFile() {
		if (!changed) {System.err.println("No changes, not need to save.");return false;}
		File f = currentFile;
		if (f!=null && f.exists()) {
			try {
				DataOutputStream writer = new DataOutputStream(new FileOutputStream(currentFile));
				writer.writeChars("test");
//				BufferedWriter buf = new BufferedWriter(new FileWriter(f));
//				buf.write(txtArea.getText());
//				buf.close();
				System.err.println("Save to "+f.getName()+" successful.");
				changed = false;	// ��אּ�L�ܧ�
				this.setNewTitle(frameTitle);	// ���Title
			} catch (Exception e) {
//				e.printStackTrace();
				currentFile = prevFile;	// ����s�ɥ��Ѯ�, �ثe�ɮקP�_���~
				showMessage(e.getLocalizedMessage());
			}
		} else {
			return saveFileAs();
		}

		return true;
	}
	
	// �t�s�s��
	public boolean saveFileAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setDialogTitle("�t�s�s��");
		
		int result =  fileChooser.showSaveDialog(this);
		
		if (result==JFileChooser.CANCEL_OPTION) {
			return false;
		} else {
			File f = fileChooser.getSelectedFile();
			
			System.err.println(fileFilter.getDescription());
			// �P�_�O�_�w�����ɦW
//			int i = f.getName().lastIndexOf('.');
//			if (!f.getName().substring(i+1).equals(".txt")) {
//				File newFile = new File(f.toString()+".txt");
//				f = newFile;
//			}
			
			try {
				f.createNewFile();
			} catch (Exception e) {
				showMessage(e.getLocalizedMessage());
			}
			prevFile = currentFile;
			currentFile = f;
			saveFile();
		}
		return true;
	}
	
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "�T������", JOptionPane.ERROR_MESSAGE);
	}
	
	// �]�w�O�_�}�ҿ���\��(True/False)
	public void ableSelection(boolean able) {
		if (able) {
			drawPanel.getSelectionObject().enableSelection();
		} else {
			drawPanel.getSelectionObject().disableSelection();
		}
	}
	
	public void setNewTitle(String title) {
		this.setTitle(title);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean getChanged() {
		return changed;
	}
	
	public DrawPanel getDrawPanel() {
		return drawPanel;
	}
	
	public StatusBar getStatusBar() {
		return statusbar;
	}


	/** InternalFrameListener Events **/
	
	public void internalFrameClosing(InternalFrameEvent arg0) {
		checkSave();
	}

	public void internalFrameActivated(InternalFrameEvent arg0) {}
	public void internalFrameClosed(InternalFrameEvent arg0) {}
	public void internalFrameDeactivated(InternalFrameEvent arg0) {}
	public void internalFrameDeiconified(InternalFrameEvent arg0) {}
	public void internalFrameIconified(InternalFrameEvent e) {}
	public void internalFrameOpened(InternalFrameEvent e) {}

}
