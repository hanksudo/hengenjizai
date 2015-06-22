import java.awt.Dimension;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class JLabelDynamicTime extends JFrame {
	int interval = 1000; // 1000 = 1��
	JLabel labTime = new JLabel();
	Calendar calendar;
	
	public JLabelDynamicTime() {
		// �]�mTimer
		TimerTask tt = new TimerTask() {
			public void run() {
				calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);	// ��
				int minute = calendar.get(Calendar.MINUTE); // ��
				int second = calendar.get(Calendar.SECOND); // ��
				labTime.setText("�ثe�ɶ�: "+String.format(" %02d:%02d:%02d", hour, minute, second));
			}
		};
		
		// �}��Timer
		new Timer().scheduleAtFixedRate(tt, 0, interval);
		this.getContentPane().add(labTime);
		this.setSize(new Dimension(200, 100));
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JLabelDynamicTime();
	}

}
