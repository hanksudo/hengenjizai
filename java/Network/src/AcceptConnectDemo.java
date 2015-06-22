import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @comment: 接受單一連線測試， 無法多人連線   Blocking
 * @author Han-Hong Wang
 * @date 2007/3/30
 */
public class AcceptConnectDemo {

	public static void main(String[] args) throws ProtocolException {
		int port;
		if (args.length == 0) {
			System.out.println("Usage: java AcceptConnectDemo [port] ");
			System.exit(1);
		}
		
		port = Integer.parseInt(args[0]);
		new AcceptConnectDemo().startServer(port);
	}
	
	private void startServer(int port) {
		try {
			ServerSocket srvSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Server Listening...");
				Socket socket = srvSocket.accept();
				System.out.println("Connection from IP: "+socket.getInetAddress().getHostAddress());
				System.out.println("Connection from host: "+socket.getInetAddress().getHostName());
			}
			
		} catch (Exception e) {
			System.out.println("yo man!!");
			e.printStackTrace();
		}
	}

}
