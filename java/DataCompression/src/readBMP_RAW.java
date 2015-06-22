import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @comment: Åª¨úBMP©ÎRAW¹ÏÀÉ
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/15
 */
public class readBMP_RAW extends JComponent {
	BufferedImage readImage = null;
	Image img = null;
	
	public readBMP_RAW(File f, String type) {
		this.setBounds(0, 0, 512, 512);
//		this.setPreferredSize(new Dimension(512, 512));
		if (type.toLowerCase().equals("bmp")) {
			try {
				readImage = ImageIO.read(f);
			} catch (Exception e) {
				e.printStackTrace();
				readImage = null;
			}
		} else {
			try {
				int showraw[] = new int[512 * 512];
				DataInputStream reader = new DataInputStream(new FileInputStream(f));

				for (int i = 0; i < 512; i++) {
					for (int j = 0; j < 512; j++) {
						int chr = reader.read();
						if (chr == -1) {break;}
						showraw[i * 512 + j] = chr | chr << 8 | chr << 16 | 0xFF000000;
					}
				}
				reader.close();

				img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(512, 512, showraw, 0, 512));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if (readImage != null) {
			g2.drawImage(readImage, 0, 0, this);
		} else {
			g2.drawImage(img, 0, 0, this);
		}
	}
}
