import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

	public static ArrayList<String> clientInputs;

	public static void main (String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9090);
		clientInputs = new ArrayList();

		if (clientInputs.isEmpty() != true) {
			for (int i = 0; i < clientInputs.toArray().length; i++) {
				clientInputs.remove(clientInputs.get(i));
			}
		}

		System.out.println("server started\nwaiting for connection on port: " + 9090 + "...");

		while (true) {
			sendMessageToClient("ola", server);
			saveMessageFromClient(server);
			readMessageFromClient(0);
		}
	}

	private static void sendMessageToClient (String message, ServerSocket server) throws IOException {
		try (Socket client = server.accept()){
			String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + message;
			client.getOutputStream().write(httpResponse.getBytes("UTF-8"));
		}
	}

	private static void saveMessageFromClient (ServerSocket server) throws IOException {
		Socket client = server.accept();
		InputStreamReader input = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(input);
		String line = reader.readLine();

		while (!line.isEmpty()) {
			String in = line;
			clientInputs.add(in);

			line = reader.readLine();
		}
	}

	private static void readMessageFromClient (int position) {
		Object[] inputs = clientInputs.toArray();
		System.out.println(inputs[position]);
	}
}
