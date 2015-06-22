import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @comment: �C�X����������T
 * @author Han-Hong Wang
 * @date 2007/3/30
 */
public class ServerInfo {
	ServerSocket srvSocket;
	InetAddress addr;
	
	public ServerInfo() {
		try {
			srvSocket = new ServerSocket(0);
			srvSocket.getInetAddress();
			addr = InetAddress.getLocalHost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getHostName() {
		return addr.getHostName();
	}
	
	public String getHostAddress() {
		return addr.getHostAddress();
	}
	
	private void startServer(int port) {		
		try {
			ServerSocket srvSocket = new ServerSocket(port);
			
			srvSocket.getInetAddress();
			// ���o������T
			InetAddress addr = InetAddress.getLocalHost();
			System.out.println("Server Information: ");
			System.out.println("  Local Host: "+addr);
			System.out.println("  Host Name: "+addr.getHostName());
			System.out.println("  IP address: "+addr.getHostAddress());
			System.out.println("  Port: "+srvSocket.getLocalPort());
			
			// ���o�D���Ҧ�IP��}
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
