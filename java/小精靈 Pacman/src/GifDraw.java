import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GifDraw {

	private JFrame frame;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		try {
			GifDraw window = new GifDraw();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application
	 */
	public GifDraw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		URL urlForImage = getClass().getResource("./images/D.gif");
		final ImageIcon i = new ImageIcon(urlForImage);
		final JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(i.getImage(), 0, 0, this);
			}
		};
		panel.setLayout(null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout());
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

	}

}