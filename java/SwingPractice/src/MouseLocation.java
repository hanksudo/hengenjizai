import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MouseLocation extends Canvas implements MouseMotionListener {
	static protected JLabel label = new JLabel("Show ");
	
	public MouseLocation() {
		addMouseMotionListener(this);
//		this.setPreferredSize(new Dimension(400, 400));
		setBackground(Color.black);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		f.setSize(new Dimension(400,200));
		f.getContentPane().add("Center", new MouseLocation());
		f.getContentPane().add("South", label);
		f.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		label.setText(e.getX()+", "+e.getY());
	}

}
