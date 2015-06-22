
public class dbtest {

	public static void main(String[] args) throws Exception{
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
		     e.printStackTrace();
		}
	}

}
