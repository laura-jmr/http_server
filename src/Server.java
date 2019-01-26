import java.io.IOException;
import java.net.*;
import java.io.File;

public class Server implements Runnable{

	private static Socket client;
	private static int port = 8080;
	private static boolean verbose = true;
	private static File webRoot = new File(".");
	private static String defaultFile = "index.html";
	private static String fileNotFound = "404";
	private static String methodNotSupported = "not_supported.html";


	public Server(Socket c) {
		client = c;
	}

	public void run() {

	}

	public static void main(String[] args) {

		try {
			ServerSocket serverConnect = new ServerSocket(port);
			System.out.println("server started\nwaiting for connection on port: " + port + "...");

			while (true) {
				Server server = new Server(serverConnect.accept());
			}

		} catch (IOException e) {
			System.err.println("server connection error: " + e.getMessage());
		}
	}
}