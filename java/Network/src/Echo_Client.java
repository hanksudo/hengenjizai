import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @comment: Echo - Client Side
 * @author Han-Hong Wang
 * @date 2007/3/29
 */
public class Echo_Client {
	public static void main(String args[]) {
		String s;

		try {
			Socket socket = new Socket("localhost", 16888);
			System.out.println("Client Connected.");

			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			// 送出訊息
			System.out.println("Send message to server...");
			out.writeUTF("Client: This is Client speaker.");
			out.flush();
			
			// 讀回訊息
			s = in.readUTF();
			System.out.println(s);

			// 關閉連接
			in.close();
			out.close();
			socket.close(); // 關閉socket
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}
}