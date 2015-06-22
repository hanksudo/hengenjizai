import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

/**
 * @comment: VQ���Y
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class VQCompression {
	File sourceFile, codFile, vqFile, decompressFile;
	String default_path = "C:\\dc\\"; // �w�]���|
	String default_file = ""; // �Ϥ��W��
	String sub_name = ""; // ���ɦW

	final int Max = 512 * 512; // �Ϥ��j�p
	int i, j, k, l, iteration, mp, nmin, kg, nk, count2, jmp, n = 8, cs = 256, no = 512 * 64;
	byte corpus[] = new byte[Max]; // �N�Ϥ�Ū�i�w�İ�
	float c[][] = new float[512 * 64][8];
	float cnt[][] = new float[1024][8];
	float Dt = 999999999, min_sum = 0;
	float davg, pdavg, error, perror = 10000;
	int count[] = new int[1024];
	int nz[] = new int[128];
	float scnt[][] = new float[1024][8];
	float dsum1[] = new float[1024];

	JFileChooser fileChooser;
	JFrame myFrame;
	JLayeredPane sourcePane, decompressPane;

	public VQCompression() {
		// ** -- Source Image File Pane
		sourcePane = new JLayeredPane();
		sourcePane.setBorder(BorderFactory.createCompoundBorder(
				// �~��
				BorderFactory.createTitledBorder("Source Image File"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		sourcePane.setOpaque(true); // �z��
		sourcePane.setBackground(Color.white); // ����
		sourcePane.setCursor(Cursor
				.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // �ƹ��˦�
		sourcePane.setPreferredSize(new Dimension(512, 512)); // �j�p

		// ** -- Decompressed Image File Pane
		decompressPane = new JLayeredPane();
		decompressPane.setBorder(BorderFactory.createCompoundBorder(
				// �~��
				BorderFactory.createTitledBorder("Decompressed Image File"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		decompressPane.setOpaque(true); // �z��
		decompressPane.setBackground(Color.white); // ����
		decompressPane.setCursor(Cursor
				.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // �ƹ��˦�
		decompressPane.setPreferredSize(new Dimension(512, 512)); // �j�p

		// ** -- SplitPane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				sourcePane, decompressPane);
		splitPane.setDividerLocation(512);

		// ** -- MainFrame
		myFrame = new JFrame("VQCompression");
		myFrame.getContentPane().add(splitPane);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setJMenuBar(createMenuBar());
		myFrame.pack();
		myFrame.setVisible(true);
	}

	// -- �N�Ϥ�Ū�J�w�İ�
	public boolean readData() {
		JFileChooser fileChooser = new JFileChooser(default_path);
		fileChooser.setSelectedFile(new File(default_file));
		fileChooser.setFileFilter(new JavaFilter("bmp", "BMP Images"));
		fileChooser.setFileFilter(new JavaFilter("raw", "RAW Images"));
		fileChooser.setDialogTitle("�}�ҹ���");
		int result = fileChooser.showOpenDialog(myFrame);

		if (result != JFileChooser.CANCEL_OPTION) {
			sourceFile = fileChooser.getSelectedFile();

			String s = sourceFile.getName();
			int pos = s.indexOf('.');
			if (pos >= 0) {
				default_file = sourceFile.getName().substring(0, pos); // ���w�]�ɦW
				sub_name = sourceFile.getName().substring(pos + 1); // ���o���ɦW
			}

			try {
				DataInputStream reader = new DataInputStream(
						new FileInputStream(sourceFile));
				sourcePane.removeAll();
				sourcePane.add(new readBMP_RAW(sourceFile, sub_name));
				reader.read(corpus, 0, Max);
			} catch (FileNotFoundException e) {
				showMessage("�䤣���ɮ�");
				return false;
			} catch (Exception e) {
				showMessage("�g�J�w�İϥ���");
				return false;
			}

			int m = 0;
			for (j = 0; j < 512 / 2; j++) {
				for (l = 0; l < 2; l++) {
					for (i = 0; i < 512 / 4; i++) {
						for (k = 0; k < 4; k++) {
							c[i + j * 128][k + l * 4] = (float) corpus[m++];

							// ���t�վ�
							if (c[i + j * 128][k + l * 4] < 0) {
								c[i + j * 128][k + l * 4] = 256 + c[i + j * 128][k
										+ l * 4];
							}
						}
					}
				}
			}
			System.err.println("Read Image Data Successful.");
			return true;
		}
		return false;
	}

	// �H�����
	public void init() {
		pdavg = (float) 999.9;
		iteration = 0;
		perror = 10000;
		error = 0;

		int Acurrent[] = new int[1024];
		int t, j, used;
		Random rand = new Random();

		k = 0;
		while (k < cs) {
			used = 1;
			while (used == 1) {
				t = rand.nextInt(no);
				Acurrent[k] = t;

				for (j = 0; j < n; j++) {
					cnt[k][j] = c[t][j];

				}

				for (j = 0; j < k; j++) {
					if (t == Acurrent[j]) {
						break;
					}
				}
				if (j == k) {
					used = 0;
				}
			}
			k++;
		}
		System.err.println("Initial Successful.");
	}

	public void repeat() {
		iteration++;
		full_search();
		error = Math.abs((davg - pdavg) / pdavg);
		System.err.println("\niteration=" + iteration + ", error=" + error
				+ ",davg=" + davg + ",pdavg=" + pdavg);
		pdavg = davg;
		if ((error > 0.01) && (perror > error)) {
			perror = error;
			repeat();
		}
		// System.err.println("Full Search & Cluster Successful.");
	}

	public void full_search() {
		float dsum = (float) 0.0, dmin;
		for (i = 0; i < cs; i++) {
			count[i] = 0;
			for (j = 0; j < n; j++) {
				scnt[i][j] = 0;
			}
		}

		for (i = 0; i < no; i++) {
			for (k = 0; k < cs; k++) {
				dsum1[k] = (float) 0.0;
				for (j = 0; j < n; j++) {
					dsum1[k] += (c[i][j] - cnt[k][j]) * (c[i][j] - cnt[k][j]);
					// System.err.println(dsum1[k]);
				}
			}

			dmin = dsum1[0];
			nmin = 0;
			for (k = 1; k < cs; k++) {
				if (dsum1[k] < dmin) {
					dmin = dsum1[k];
					nmin = k;
				}
			}
			count[nmin]++;
			dsum += dmin;
			for (j = 0; j < n; j++) {
				scnt[nmin][j] += c[i][j];
			}
		}

		mp = 1;
		for (k = 0; k < cs; k++) {
			if (count[k] == 0) {
				nz[mp] = k;
				mp = mp + 1;
			}
		}

		if (mp > 1) {
			count2 = count[0];
			for (kg = 1; kg < cs; kg++) {
				if (count2 < count[kg]) {
					count2 = count[kg];
					nk = kg;
				}
			}

			for (j = 0; j < n; j++) {
				cnt[nk][j] = (float) (cnt[nk][j] - 0.0001);
				for (jmp = 1; jmp < mp; jmp++) {
					cnt[nz[jmp]][j] = (float) (cnt[nk][j] + 0.0001 * jmp);
				}
			}

			full_search();
		} else {

			for (k = 0; k < cs; k++) {
				for (j = 0; j < n; j++) {
					cnt[k][j] = scnt[k][j] / count[k];
				}
			}
			davg = dsum / (no * n);
			System.err.println("\ndavg=" + davg + ", dsum=" + dsum + ", no="
					+ no);
		}
	}

	// -- CodeWord�s��
	public void save_codeword() {
		fileChooser = new JFileChooser(default_path);
		fileChooser.setSelectedFile(new File(default_file + ".cod"));
		fileChooser.setDialogTitle("�x�sCodeword��");

		int result = fileChooser.showSaveDialog(myFrame);

		if (result != JFileChooser.CANCEL_OPTION) {
			codFile = fileChooser.getSelectedFile();
			try {
				DataOutputStream writer = new DataOutputStream(
						new FileOutputStream(codFile));
				for (i = 0; i < cs; i++) {
					for (j = 0; j < n; j++) {
						writer.writeFloat(cnt[i][j]);
					}
				}
				System.err.println("Save Codeword " + codFile.getName()
						+ " Successful.");
			} catch (Exception e) {
				showMessage("Save Codeword File Error!!");
				e.printStackTrace();
			}
		}
	}

	// -- CodeWordŪ��
	public void open_codeword() {
		fileChooser = new JFileChooser(default_path);
		fileChooser.setDialogTitle("�}��Codeword��");
		if (fileChooser.showOpenDialog(myFrame) != JFileChooser.CANCEL_OPTION) {
			codFile = fileChooser.getSelectedFile();
			try {
				DataInputStream reader = new DataInputStream(
						new FileInputStream(codFile));
				for (i = 0; i < cs; i++) {
					for (j = 0; j < n; j++) {
						cnt[i][j] = reader.readFloat();
					}
				}
				System.err.println("Open Codeword " + codFile.getName()
						+ " Successful.");
			} catch (Exception e) {
				showMessage("Open Codeword File Error!!");
				e.printStackTrace();
			}
		}
	}

	// -- �N�p��X�����Y��(.vq)�x�s
	public void save_vq() {
		fileChooser = new JFileChooser(default_path);
		fileChooser.setSelectedFile(new File(default_file + ".cmp"));
		fileChooser.setDialogTitle("�x�s���Y��");
		int result = fileChooser.showSaveDialog(myFrame);

		if (result != JFileChooser.CANCEL_OPTION) {
			vqFile = fileChooser.getSelectedFile();
			int nmin;
			float dsum1[] = new float[1024];
			float dmin;

			try {
				DataOutputStream writer = new DataOutputStream(
						new FileOutputStream(vqFile));
				for (i = 0; i < no; i++) {
					for (k = 0; k < cs; k++) {
						dsum1[k] = (float) 0.0;
						for (j = 0; j < n; j++) {
							dsum1[k] += (c[i][j] - cnt[k][j])
									* (c[i][j] - cnt[k][j]);
						}
					}
					dmin = dsum1[0];
					nmin = 0;

					for (k = 1; k < cs; k++) {
						if (dsum1[k] < dmin) {
							dmin = dsum1[k];
							nmin = k;
						}
					}

					for (j = 0; j < n; j++) {
						c[i][j] = cnt[nmin][j];
					}

					writer.write(nmin);
				}
				System.err.println("Save VQ " + vqFile.getName()
						+ " Successful.");
			} catch (Exception e) {
				showMessage("Save VQ Error.");
				e.printStackTrace();
			}
		}
	}

	public void load_vq() {
		fileChooser = new JFileChooser(default_path);
		fileChooser.setDialogTitle("Ū�����Y��");

		if (fileChooser.showOpenDialog(myFrame) != JFileChooser.CANCEL_OPTION) {
			vqFile = fileChooser.getSelectedFile();
			int nmin;

			try {
				DataInputStream reader = new DataInputStream(
						new FileInputStream(vqFile));
				for (i = 0; i < no; i++) {
					nmin = reader.read();

					for (j = 0; j < n; j++) {
						c[i][j] = cnt[nmin][j];
					}
				}
				System.err.println("Load compress " + vqFile.getName()
						+ " Successful.");
			} catch (Exception e) {
				showMessage("Load Compress File Error.");
				e.printStackTrace();
			}
		}
	}

	// -- �x�s�����Y������
	public void save_decompressed() {
		fileChooser = new JFileChooser(default_path);
		fileChooser
				.setSelectedFile(new File(default_file + "-rec." + sub_name));
		fileChooser.setDialogTitle("�x�s�����Y����");
		int result = fileChooser.showSaveDialog(myFrame);

		if (result != JFileChooser.CANCEL_OPTION) {
			decompressFile = fileChooser.getSelectedFile();
			int m = 0;
			for (j = 0; j < 512 / 2; j++) {
				for (l = 0; l < 2; l++) {
					for (i = 0; i < 512 / 4; i++) {
						for (k = 0; k < 4; k++) {
							corpus[m++] = (byte) c[i + j * 128][k + l * 4];
						}
					}
				}
			}

			try {
				DataOutputStream writer = new DataOutputStream(
						new FileOutputStream(decompressFile));
				writer.write(corpus);
				System.err.println("Save Decompressed Image "
						+ decompressFile.getName() + " Successful.");
				decompressPane.removeAll();
				decompressPane.add(new readBMP_RAW(decompressFile, sub_name));
			} catch (Exception e) {
				showMessage("Save Decompressed Image Error!!.");
				e.printStackTrace();
			}
		}
	}

	processAction exitAction;
	processAction openAction, savecodAction, opencodAction, savevqAction, openvqAction, savedecAction, psnrAction;
	processAction aboutAction;

	public JMenuBar createMenuBar() {

		// �إ� JMenuBar
		JMenuBar myMenu = new JMenuBar();
		int menuID;

		// �إ�JMenu
		String Menus[] = { "File", "Functions", "Help" }; // ���W��
		char Mnemonics[] = { 'F', 'U', 'H' }; // �ֱ�
		JMenu[] mMenu = new JMenu[Menus.length];

		for (int i = 0; i < Menus.length; i++) {
			mMenu[i] = new JMenu(Menus[i]); // Create Menu
			mMenu[i].setMnemonic(Mnemonics[i]); // Set Mnemonic
			myMenu.add(mMenu[i]); // Add to MenuBar
		}

		// FirstMenu - File Menu
		menuID = 0;
		addMenuItem(mMenu[menuID], exitAction = new processAction("Exit",
				"Close this program.", "alt F4", 'X'));

		// SecondMenu - Edit Menu
		menuID = 1;
		addMenuItem(mMenu[menuID], openAction = new processAction(
				"Open Source File", "Open a image file.", "ctrl O", 'O'));
		mMenu[menuID].addSeparator();
		addMenuItem(mMenu[menuID], savecodAction = new processAction(
				"Save Codeword", "Save Codeword File.", ""));
		addMenuItem(mMenu[menuID], opencodAction = new processAction(
				"Open Cordword", "Open a Codeword file.", ""));
		mMenu[menuID].addSeparator();
		addMenuItem(mMenu[menuID], savevqAction = new processAction(
				"Save Compressed File", "Save Compressed File.", ""));
		addMenuItem(mMenu[menuID], openvqAction = new processAction(
				"Open Compressed File", "Open Compressed File.", ""));
		mMenu[menuID].addSeparator();
		addMenuItem(mMenu[menuID], savedecAction = new processAction(
				"Save Decompressed File", "Save Decompressed File.", ""));
		mMenu[menuID].addSeparator();
		addMenuItem(mMenu[menuID], psnrAction = new processAction("PSNR...",
				"PSNR Analysis", ""));

		// Third Menu - Help Menu
		menuID = 2;
		addMenuItem(mMenu[menuID], aboutAction = new processAction("About",
				"About this program.", "", 'A'));

		return myMenu;
	}

	class processAction extends SetAction {
		public processAction(String name, String tooltip, String keystroke,
				char mnemonic) {
			super(name, tooltip, keystroke, mnemonic);
		}

		public processAction(String name, String tooltip, String keystroke) {
			super(name, tooltip, keystroke);
		}

		public void actionPerformed(ActionEvent e) {
			if (this == exitAction) {
				myFrame.dispose();
			}
			if (this == openAction) {
				if (readData()) {
					Thread thread = new Thread(new Runnable() {
						public void run() {
							init();
							repeat();
						}
					});
					thread.setDaemon(true);
					thread.start();
				}
			}
			if (this == savecodAction) {save_codeword();}
			if (this == opencodAction) {open_codeword();}
			if (this == savevqAction) {save_vq();}
			if (this == openvqAction) {load_vq();}
			if (this == savedecAction) {save_decompressed();}
			if (this == psnrAction) {
				Thread psnrThread = new Thread(new Runnable() {
					public void run() {
						Psnr p = new Psnr();
						p.calculate(512, 512, sourceFile.toString(), decompressFile.toString());
						p.showPSNR(myFrame);
					}
				});
				psnrThread.setDaemon(true);
				psnrThread.start();
			}
		}
	}

	private void addMenuItem(JMenu menu, Action action) {
		menu.add(action);
	}

	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(myFrame, msg, "�T������",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		new VQCompression();
	}

}
