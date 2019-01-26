import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.StringTokenizer;

public class Server implements Runnable{

	private static Socket client;
	private static int port = 8080;
	private static boolean verbose = true;
	private static File webRoot = new File(".");
	private static String defaultFile = "index.html";
	private static String fileNotFound = "404.html";
	private static String methodNotSupported = "not_supported.html";


	public Server(Socket c) {
		client = c;
	}

	public void run() {
		BufferedReader input = null;
		PrintWriter output = null;
		BufferedOutputStream dataOutput = null;
		String fileRequest = null;

		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream());
			dataOutput = new BufferedOutputStream(client.getOutputStream());

			String inputString = input.readLine();
			StringTokenizer parse = new StringTokenizer(inputString);
			String method = parse.nextToken().toUpperCase();
			fileRequest = parse.nextToken().toLowerCase();

			if (!method.equals("get") && !method.equals("head")) {
				if (verbose) {
					System.out.println("501 not implememted: " + method + " method");
				}

				File file = new File(webRoot, methodNotSupported);
				int fileLength = (int) file.length();
				String contentMimeType = "text/htmL";

				byte[] fileData = readFileData(file, fileLength);

				output.println("http/1.1 501 not implemented");
				output.println("server: java http server from laura-jmr");
				output.println("date: " + new Date());
				output.println("content-type: " + contentMimeType);
				output.println("content-length: " + fileLength);
				output.println();
				output.flush();

				dataOutput.write(fileData, 0, fileLength);
				dataOutput.flush();

				return;
			}


		} catch (IOException ioe) {
			System.err.println("server error: " + ioe);
		}
	}

	private byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];

		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
		}

		return fileData;
	}

	public static void main(String[] args) {

		try {
			ServerSocket serverConnect = new ServerSocket(port);
			System.out.println("server started\nwaiting for connection on port: " + port + "...");

			while (true) {
				Server server = new Server(serverConnect.accept());

				if (verbose) {
					System.out.println("connection opened --" + new Date() + "--");
				}

				Thread thread = new Thread(server);
				thread.start();
			}

		} catch (IOException e) {
			System.err.println("server connection error: " + e.getMessage());
		}
	}
}