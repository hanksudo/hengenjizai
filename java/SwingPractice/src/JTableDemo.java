import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class JTableDemo extends JFrame {

	public JTableDemo() {
		String[] columnNames = {"Name", "Age", "Sex"};

		Object[][] data = {
				{"�_�n", "24", "�k"},
				{"����", "24", "�k"},
				{"�~��", "22", "�k"},
		};

		JTable table = new JTable(data, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
			
		JScrollPane scrollpane = new JScrollPane(table);
		this.getContentPane().add(scrollpane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JTableDemo();
	}

}
