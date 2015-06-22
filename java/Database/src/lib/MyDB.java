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
//		String dbName = "test";	// 資料庫名稱
		String dbUser = "root";	// 帳號
		String dbPassword = "mysql2009";	// 密碼
		String dbURL = "localhost";	// 資料庫位址
		try {
			Properties p = new Properties();

			// 設定連線使用編碼為UTF8
			p.put("characterEncoding", "UTF8");
			p.put("useUnicode", "TRUE");
			
			//設定連線時使用的帳號及密碼
			p.setProperty("user", dbUser);
			p.setProperty("password", dbPassword);
			
			//載入MySQL Driver
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("MySQL模組載入完成");
			
			myConnection = DriverManager.getConnection("jdbc:mysql://"+dbURL+"/"+dbName, p);
//			System.out.println("MySQL連線成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "資料庫連結失敗", "錯誤", JOptionPane.ERROR_MESSAGE);
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
