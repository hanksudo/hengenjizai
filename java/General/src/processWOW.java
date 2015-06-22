import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;


public class processWOW {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\test.txt"),Charset.forName("utf8")));
		String temp;
		while(br.ready()) {
			temp = br.readLine();
			if (temp.indexOf("{L_ATTENDEES}")>0) {
				String id = temp.substring(temp.indexOf("id\\")+10, temp.indexOf("id\\")+12).replace('\\', ' ').trim();
				String name = temp.substring(temp.indexOf("{L_ATTENDEES}")+21,temp.indexOf("{L_NOTE}")-5);
				String names[] = name.split(",");
				String temp2="";
				for(int i=0;i<names.length;i++) {
					if(temp2!="") {temp2+=",";}
					temp2+="("+id+",'"+names[i].trim()+"')";
				}
				System.out.println("Insert Into eqdkp_raid_attendees (`raid_id`,`member_name`)Values"+temp2+";");
//				String outtxt = "Insert Into eqdkp_raid_attendees (`raid_id`,`member_name`)Values"+temp2+";";
//				 
//				FileOutputStream fos = new FileOutputStream("C:\\test2.sql");
//				OutputStreamWriter out = new OutputStreamWriter(fos,Charset.forName("utf-8"));
////				Writer out = new OutputStreamWriter(fos, "utf-8");  
//				out.write(outtxt);  
//				out.close();  
//				fos.close();  
				
			}	
		}	
	}

}
