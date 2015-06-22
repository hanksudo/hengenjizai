package Access;
import java.sql.*;

public class AccessJDBCUtil {
    private static final String accessDBURLPrefix = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
    private static final String accessDBURLSuffix = ";DriverID=22;READONLY=true}";
    static {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch(ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
            // ABORT ABORT... How? System.exit(1) is not nice from webapp...
        }
    }
    
    /** Creates a Connection to a Access Database */
    public static java.sql.Connection getAccessDBConnection(String filename) throws SQLException {
        filename = filename.replace('\\', '/').trim();
        String databaseURL = accessDBURLPrefix + filename + accessDBURLSuffix;
        // System.err.println("Datebase URL: " + databaseURL);
        return DriverManager.getConnection(databaseURL, "", "");
    }  
}