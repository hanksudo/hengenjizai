import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class JTableExample extends JFrame {

	private static final String dbDriverClass = "com.mysql.jdbc.Driver";

	static {
		try {
			try {
				Thread
					.currentThread()
					.getContextClassLoader()
					.loadClass(dbDriverClass)
					.newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JButton btnConnect, btnRefresh;
	private JTextField txtSQL;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private javax.swing.JPanel btnPanel, contentPanel;

	private final String dbName = "districtmanage";
	private final int dbPort = 3306;
	private final String dbURL =
		"jdbc:mysql://localhost:" + dbPort + "/" + dbName;
	private final String dbUser = "root";
	private final String dbpasswd = "test";

	private MysqlDataSource ds;
	private Connection con;
	private ResultSet rs;

	public JTableExample() {
		super("JTableExample");

		GridBagConstraints gridBagConstraints;

		contentPanel = new JPanel();
		btnPanel = new JPanel();
		btnConnect = new JButton();
		btnRefresh = new JButton();
		txtSQL = new JTextField();
		scrollPane = new JScrollPane();
		table = new JTable();
		dtm = new DefaultTableModel();

		getContentPane().setLayout(new FlowLayout());

		setTitle("JTableExample");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					if (con != null && !con.isClosed()) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		contentPanel.setLayout(new GridBagLayout());

		contentPanel.setBackground(new Color(102, 153, 255));
		contentPanel.setMinimumSize(new Dimension(400, 300));
		contentPanel.setPreferredSize(new Dimension(400, 300));
		btnPanel.setBackground(new Color(255, 153, 51));
		btnPanel.setMinimumSize(new Dimension(400, 100));
		btnPanel.setPreferredSize(new Dimension(400, 100));
		btnConnect.setText("Connect");
		btnPanel.add(btnConnect);

		btnRefresh.setText("Refresh");
		btnPanel.add(btnRefresh);

		txtSQL.setColumns(25);
		btnPanel.add(txtSQL);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		contentPanel.add(btnPanel, gridBagConstraints);

		scrollPane.setMinimumSize(new Dimension(400, 200));
		scrollPane.setPreferredSize(new Dimension(400, 200));
		table.setModel(dtm);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		contentPanel.add(scrollPane, new GridBagConstraints());

		getContentPane().add(contentPanel);

		setSize(400, 300);

		Container container = getContentPane();
		//con.setLayout(FillLayout());

		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				connect();
			}

		});
		btnRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refresh();
			}

		});

		setVisible(true);
	}

	/**
	 * 
	 */
	protected void refresh() {
		// TODO Auto-generated method stub
		try {
			if (con == null || con.isClosed()) {
				JOptionPane.showMessageDialog(
					this,
					"Keine Datenbaknverbindung!",
					"Fehler",
					JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (stmt == null) {
			throw new RuntimeException("stmt is null");
		}

		String sql = txtSQL.getText();
		if (sql == null) {
			JOptionPane.showMessageDialog(
				this,
				"Keine SQL Abfrage angegeben!",
				"Fehler",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		putRSinTableModel(rs, dtm);

		table.setModel(dtm);

		table.updateUI();

	}

	/**
	 * @param rs
	 * @param dtm
	 */
	private void putRSinTableModel(ResultSet rs, DefaultTableModel dtm) {
		// TODO Auto-generated method stub
		ResultSetMetaData rsmd = null;
		int clmCnt = -1;
		try {
			rsmd = rs.getMetaData();
			clmCnt = rsmd.getColumnCount();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rsmd == null || clmCnt == -1) {
			throw new RuntimeException("rsmd is null");
		}
		try {
			rs.first();
			rs.last();
			int rowCnt = rs.getRow();
			rs.first();

			Object[][] odata = new Object[rowCnt][clmCnt];
			Object[] clmHeaders = new Object[clmCnt];
			for (int i = 1; i <= clmCnt; i++) {
				clmHeaders[i - 1] = rsmd.getColumnName(i);
			}

			int row = 0;
			if (rs.next()) {
				rs.first();
			do {
				for (int i = 1; i <= clmCnt; i++) {
					System.out.print(rs.getString(i) + " ");
					odata[row][i - 1] = rs.getString(i);
				}
				row++;
				System.out.println(row);
			} while (rs.next());
			}

			dtm.setDataVector(odata, clmHeaders);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * 
	 */
	protected void connect() {
		// TODO Auto-generated method stub
		if(con != null)
			return;
		ds = new MysqlDataSource();
		ds.setDatabaseName(dbName);
		ds.setPort(dbPort);
		ds.setUser(dbUser);
		ds.setPassword(dbpasswd);
		ds.setURL(dbURL);
		ds.setCharacterEncoding("UTF8");
		
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new JTableExample();
	}
}