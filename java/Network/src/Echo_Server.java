import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @comment: Echo - Server Side
 * @author Han-Hong Wang
 * @date 2007/3/29
 */
public class Echo_Server {
	public static void main(String args[]){
		String s;

		try{
			int port = 16888;
			ServerSocket server=new ServerSocket(port);
			System.out.println("Serever Listening...");
			Socket socket=server.accept();	// Server Listen
			System.out.println("Server Connected.");
			
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			// 讀回訊息
			s = in.readUTF();
			System.out.println(s);
			
			// 送出訊息
			System.out.println("Send a message to client...");
			out.writeUTF("Server: This is Server echo speaker.");
			out.flush();			

			// 關閉連接
			in.close();
			out.close();
			socket.close(); // 關閉socket
		}
		catch(Exception e){
			System.out.println("Error:"+e);
		}
	}
}