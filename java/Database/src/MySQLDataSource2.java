import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySQLDataSource2 {
	Properties p = new Properties();
	String database="test", dbURL="localhost", dbPort="3306";
	public MySQLDataSource2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.err.println("MySQL載入正常.");
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL載入錯誤!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+dbURL+":"+dbPort+"/"+database,p);
			System.err.println("登入資料庫成功!!");
			return conn;
		} catch (SQLException e) {
			System.err.println("登入資料庫失敗，請檢查帳密或連線參數!!");
			return null;
		}
	}
	
	// 資料表名稱
	public void setDatabaseName(String dbName) {
		database = dbName;
	}
	
	public void setUser(String dbUser) {
		p.put("user", dbUser);
	}
	
	public void setPassword(String dbpasswd) {
		p.put("password", dbpasswd);
	}
	
	public void setURL(String url) {
		dbURL = url;
	}
	
	public void setPort(String port) {
		dbPort = port;
	}
}
