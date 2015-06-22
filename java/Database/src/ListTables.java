/************************************************
*  Comment: �C�X�Ҧ���ƪ�						*
*  Author: W.H.H. (����)						*
*  Website: http://www.whh.idv.tw				*
*  E-mail: cohere.wind@msa.hinet.net			*
*  Develop Environment-Compiler: j2sdk1.4.2_06	*
*		      -Editor: JCreator	3.50			*
*		      -OS: Windows 2000 Server			*
*  Creation Date: 2005/06/20					*
*  Last Update: 2005/06/20						*
*************************************************/
import java.sql.*;

public class ListTables {
	public static void main(String args[]) throws Exception{
		
    try {
    	Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL Driver ���J���`!!");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","test");
 
		System.out.println("�ϥΪ̤w�n�J��Ʈw!!");              
        // Gets the database metadata
        DatabaseMetaData dbmd = con.getMetaData();
    
        // Specify the type of object; in this case we want tables
        String[] types = {"TABLE"};
        ResultSet resultSet = dbmd.getTables(null, null, "%", types);
    
        // Get the table names
        while (resultSet.next()) {
            // Get the table name
            String tableName = resultSet.getString(3);
    
            // Get the table's catalog and schema names (if any)
            String tableCatalog = resultSet.getString(1);
            String tableSchema = resultSet.getString(2);
            System.out.println("Table Name : "+tableName);
            System.out.println("Table Catalog : "+tableCatalog);
            System.out.println("Table Schema : "+tableSchema);
            System.out.println();
        }
    } catch (SQLException e) {
		System.out.println("Error!! ��Ʈw�L�k�n�J!!");          
		System.out.println("SQLException = "+e.getMessage());          
		System.out.println("SQLState = "+e.getSQLState());          
		System.out.println("VendorError = "+e.getErrorCode());              
    }
    }
}