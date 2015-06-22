import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class JTableDemo2 extends JFrame {

	public JTableDemo2() {
		String[] columnNames = {"Name", "Age", "Sex"};

		Object[][] data = {
				{"柯南", "24", "男"},
				{"阿牧", "24", "男"},
				{"漢宏", "22", "男"},
		};

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Sex");
		
		model.addRow(data[0]);
		model.addRow(data[1]);
		model.addRow(data[2]);
		
		
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
			
		JScrollPane scrollpane = new JScrollPane(table);
		this.getContentPane().add(scrollpane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JTableDemo2();
	}

}
