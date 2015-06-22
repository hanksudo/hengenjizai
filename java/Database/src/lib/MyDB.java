package lib;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class MyDB {
	boolean debug = false;
	private Connection myConnection;
	private ResultSet rs;

	static {
		try {
			try {
				Thread
					.currentThread()
					.getContextClassLoader()
					.loadClass("com.mysql.jdbc.Driver")
					.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Creates a new instance of MyDBConnection */
	public MyDB(String dbName) {
//		String dbName = "test";	// ��Ʈw�W��
		String dbUser = "root";	// �b��
		String dbPassword = "mysql2009";	// �K�X
		String dbURL = "localhost";	// ��Ʈw��}
		try {
			Properties p = new Properties();

			// �]�w�s�u�ϥνs�X��UTF8
			p.put("characterEncoding", "UTF8");
			p.put("useUnicode", "TRUE");
			
			//�]�w�s�u�ɨϥΪ��b���αK�X
			p.setProperty("user", dbUser);
			p.setProperty("password", dbPassword);
			
			//���JMySQL Driver
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("MySQL�Ҳո��J����");
			
			myConnection = DriverManager.getConnection("jdbc:mysql://"+dbURL+"/"+dbName, p);
//			System.out.println("MySQL�s�u���\");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��Ʈw�s������", "���~", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Connection getMyConnection() {
		return myConnection;
	}
	
	public Statement getStmt() {
		Statement stmt = null;
		try {
			stmt = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
	}

	public ResultSet getResultSet() {
		return rs;
	}
	
	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}

		}
	}

	public void close(Statement stmt) {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}

		}
	}
	
	public void deleteExc(String sql) {
		try {
			this.getStmt().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean refresh(String sql) {
		if (debug) {System.err.println(sql);}
		
		try {
			if (this.getMyConnection() == null || this.getMyConnection().isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Statement stmt = this.getStmt();

		if (stmt == null) {	throw new RuntimeException("stmt is null");	}

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e2) {
			System.err.println(e2.getMessage());
		}
		
		return true;
	}
	
	
	
	public void destroy() {

		if (myConnection != null) {

			try {
				myConnection.close();
			} catch (Exception e) {
			}

		}
	}

}
