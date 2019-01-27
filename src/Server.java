import java.net.*;
import java.io.*;

public class Server {

	public static void main (String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9090);

		System.out.println("server started\nwaiting for connection on port: " + 9090 + "...");

		while (true) {
			sendMessageToClient("ola", server);
			readMessageFromClient(server);
		}
	}

	private static void sendMessageToClient (String message, ServerSocket server) throws IOException {
		try (Socket client = server.accept()){
			String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + message;
			client.getOutputStream().write(httpResponse.getBytes("UTF-8"));
		}
	}

	private static void readMessageFromClient (ServerSocket server) throws IOException {
		Socket client = server.accept();
		InputStreamReader input = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(input);
		String line = reader.readLine();

		while (!line.isEmpty()) {
			System.out.println(line);

			line = reader.readLine();
		}
	}
}
