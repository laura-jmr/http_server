import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {

	private ServerSocket serverSocket;
	private Socket client;
	private int port;
	private static boolean stop;

	public Server(int port1) {
		port = port1;
		stop = false;

		try {
			serverSocket = new ServerSocket(port);

			serverSocket.setSoTimeout(1000000);
		} catch (SocketException e) {} catch (IOException e) {}
	}

	public void activate() {

		System.out.println("waitint for client at " + port);

		try {
			client = serverSocket.accept();
			//DataInputStream input = new DataInputStream(client.getInputStream());
			DataOutputStream output = new DataOutputStream(client.getOutputStream());

			//System.out.println(client.getRemoteSocketAddress() + ": " + input.readUTF());
			System.out.println(client.getRemoteSocketAddress());
			output.writeUTF("ola");

			System.out.println("handled");

			client.close();
		} catch (Exception e) {
			System.out.println("stopped");
			stop = true;
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(8080);

		while (stop == false) {
			server.activate();
		}
	}
}