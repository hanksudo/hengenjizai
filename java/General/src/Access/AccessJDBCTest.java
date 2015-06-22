package Access;
import java.sql.*;
import java.io.*;

// First argument is database file name
// Second argument is your query in quotes
public class AccessJDBCTest {
    public static void main(String args[]) {
        if(args.length != 2) {
            System.out.println("Example: java AccessJDBCTest database_name.mdb \"database query\"");
            System.exit(1);
        }
        java.sql.Connection conn = null;
        try {
            conn = AccessJDBCUtil.getAccessDBConnection(args[0]);
            Statement stmt = conn.createStatement();
            if(stmt.execute(args[1])) {
                printResultSet(System.out, stmt.getResultSet(), "Query Result");
            } else {
                System.out.println("DDL executed successfully");
            }
        } catch(SQLException s) {
            System.out.println(s);
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch(SQLException ignore) {}
            }
        }
     }
     
     /** Prints badly ResultSet to PrintStream */
    public static void printResultSet(PrintStream p, ResultSet rs, String title) throws SQLException {
       if(rs != null) {
            ResultSetMetaData metaData = rs.getMetaData();
            int cols = metaData.getColumnCount();
            p.println("\n--------------------------\n" + title + "\n--------------------------");
            for(int i = 1;i <= cols;i++) {
                p.print(metaData.getColumnLabel(i) + "\t");
            }
            p.println("\n--------------------------");
            int count = 0;
            while(rs.next()) {
                for(int i = 1;i <= cols;i++) {
                    p.print(rs.getString(i) + "\t");
                }
                p.println("\n--------------------------");
                count++;
            }
            p.println("Rows: " + count);
       }
    } 
}