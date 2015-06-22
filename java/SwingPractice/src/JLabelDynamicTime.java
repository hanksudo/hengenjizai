import java.awt.Dimension;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class JLabelDynamicTime extends JFrame {
	int interval = 1000; // 1000 = 1秒
	JLabel labTime = new JLabel();
	Calendar calendar;
	
	public JLabelDynamicTime() {
		// 設置Timer
		TimerTask tt = new TimerTask() {
			public void run() {
				calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);	// 時
				int minute = calendar.get(Calendar.MINUTE); // 分
				int second = calendar.get(Calendar.SECOND); // 秒
				labTime.setText("目前時間: "+String.format(" %02d:%02d:%02d", hour, minute, second));
			}
		};
		
		// 開啟Timer
		new Timer().scheduleAtFixedRate(tt, 0, interval);
		this.getContentPane().add(labTime);
		this.setSize(new Dimension(200, 100));
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JLabelDynamicTime();
	}

}
