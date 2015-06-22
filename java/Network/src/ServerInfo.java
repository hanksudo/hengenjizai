import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @comment: 列出本機網路資訊
 * @author Han-Hong Wang
 * @date 2007/3/30
 */
public class ServerInfo {

	public static void main(String[] args) {
		int port;
		
		if (args.length == 0) {
			System.out.println("Usage: java ServerInfo [port] ");
			System.exit(1);
		}
		
		port = Integer.parseInt(args[0]);
		new ServerInfo().startServer(port);
		
	}
	
	private void startServer(int port) {		
		try {
			ServerSocket srvSocket = new ServerSocket(port);
			
			// 取得本機資訊
			InetAddress addr = srvSocket.getInetAddress().getLocalHost();
			System.out.println("Server Information: ");
			System.out.println("  Local Host: "+addr);
			System.out.println("  Host Name: "+addr.getHostName());
			System.out.println("  IP address: "+addr.getHostAddress());
			System.out.println("  Port: "+srvSocket.getLocalPort());
			
			// 取得主機所有IP位址
			InetAddress addrs[] = srvSocket.getInetAddress().getAllByName(addr.getHostName());
			System.out.println("IP address(es): ");
			for (int i=0; i<addrs.length; i++) {
				System.out.println("  "+addrs[i].getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
