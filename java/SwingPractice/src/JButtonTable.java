import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
 
public class JButtonTable extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private TableModel tableModel = null;  //  @jve:decl-index=0:
 
	/**
	 * This is the default constructor
	 */
	public JButtonTable() {
		super();
		this.setSize(300, 200);
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(getJTable());
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());
		jContentPane.add(jScrollPane, BorderLayout.CENTER);
		this.setContentPane(jContentPane);
		this.setTitle("JFrame");
	}
	
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(getTableModel());
			jTable.getColumn("").setMaxWidth(130);
			jTable.getColumn("").setCellRenderer(new JTableButtonRenderer());
			jTable.getColumn("").setCellEditor(new JTableButtonEditor());
		}
		return jTable;
	}
 
	/**
	 * This method initializes tableModel	
	 * 	
	 * @return javax.swing.table.AbstractTableModel	
	 */
	private TableModel getTableModel() {
		if (tableModel == null) {
			tableModel = new AbstractTableModel() {
				private String[][] rows = {{"00001", "user1", "John Doe", "john@doe"},
						{"00002", "user2", "Jane Doe", "jane@doe"},
				};
				private static final long serialVersionUID = 1L;
				private String[] headings = new String[] {
						"", "Username", "CommonName", "Email"
				};
 
				public int getColumnCount() {
					return headings.length;
				}
				
				public String getColumnName(int column) {
					return headings[column];
				}
 
				public int getRowCount() {
					return rows.length;
				}
				
				public boolean isCellEditable(int row, int column) {
					if (column == 0) return true;
					else return false;
				}
 
				public Class<?> getColumnClass(int columnIndex) {
					return getValueAt(0, columnIndex).getClass();
				}
	
				public Object getValueAt(int rowIndex, int columnIndex) {
					Object value = null;
					if (columnIndex == 0) value = createButton(rows[rowIndex][0]);
					else value = rows[rowIndex][columnIndex];
					return value;
				}
			};
		}
		return tableModel;
	}
	
	/**
	 * This method generates a button for the table, setting a dummy ActionListener
	 * 	
	 * @return javax.swing.JButton;
	 */
	private JButton createButton(String id) {
		JButton jButton = new JButton("edit");
		jButton.setActionCommand(id);
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("Edit user" + e.getActionCommand());
			}
		});
		return jButton;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JButtonTable thisClass = new JButtonTable();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
 
	/**
	 * 	This class is responsible for actually drawing the button, it returns whatever java.awt.Compenent is in the table, since components can render themselves 
	 *
	 */
	private class JTableButtonRenderer extends JButton implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			return (Component)value;
		}
 
	}
	
	/**
	 *	This class is what the table cals when you click on the cell, to "edit" it. 
	 *
	 */
	private class JTableButtonEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		JButton jButton = null;
		
		public JTableButtonEditor() {
			jButton = new JButton();
		}
		
		/**
		 * This method returns the java.awt.Component that is in the table cell as that cells editor
		 */
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			return (JButton) table.getValueAt(row, column);
		}
 
		/**
		 * This mehtod always returns the same jButton, I guess it defines the behavior of the editor.
		 */
		public Object getCellEditorValue() {
			return jButton;
		}
	}
	
}
