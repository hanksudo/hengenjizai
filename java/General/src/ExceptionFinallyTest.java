import java.io.*;

public class ExceptionFinallyTest {    
	public static void main(String argv[]) {	
		ExceptionFinallyTest m=new ExceptionFinallyTest();	
		System.out.println(m.amethod());    
	}    

	public int amethod() {	
		try {	    
			FileInputStream dis=new FileInputStream("C:\\Hello.txt");	
		} catch (FileNotFoundException fne) {	    
			System.out.println("No such file found");	    
			return -1;	
		} catch(IOException ioe) {	
		} finally{	    
			System.out.println("Doing finally");	
		}	
		return 0;    
	}
}