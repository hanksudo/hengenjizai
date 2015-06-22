import java.sql.*;
public class ConnectTest{
	public static void main(String args[]) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL Driver ���J���`!!");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=big5","root","mysql2009");
			System.out.println("�ϥΪ̤w�n�J��Ʈw!!");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * From members");
				while (rs.next()) {
					System.out.println(rs.getString(2));
				}
			}
			catch(ClassNotFoundException e){           
				System.out.println("Error!! MySQL Driver ���J���~!!");              
			}
			catch(SQLException e){            
				System.out.println("Error!! ��Ʈw�L�k�n�J!!");          
				System.out.println("SQLException = "+e.getMessage());          
				System.out.println("SQLState = "+e.getSQLState());          
				System.out.println("VendorError = "+e.getErrorCode());              
			}     
	}
}