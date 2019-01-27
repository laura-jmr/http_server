import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.StringTokenizer;

public class server_old {
/*
	public static void main(String[] args) throws Exception {
		int port = 9090;
		final ServerSocket serverCon = new ServerSocket(port);

		System.out.println("server started\nwaiting for connection on port: " + port + "...");

		while (true) {
			final Socket client = new serverCon.accept();

				if (verbose) {
					System.out.println("connection opened --" + new Date() + "--");
				}
			}
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
				String contentMimeType = "text/html";

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
			}
			else {
				if (fileRequest.endsWith("/")) {
					fileRequest += defaultFile;
				}

				File file = new File(webRoot, fileRequest);
				int fileLength = (int) file.length();
				String content = getContentType(fileRequest);

				if (method.equals("get")) {
					byte[] fileData = readFileData(file, fileLength);

					output.println("http/1.1 200 ok");
					output.println("server: java http server from laura-jmr");
					output.println("date: " + new Date());
					output.println("content-type: " + content);
					output.println("content-length: " + fileLength);
					output.println();
					output.flush();

					dataOutput.write(fileData, 0, fileLength);
					dataOutput.flush();
				}

				if (verbose) {
					System.out.println("File " + fileRequest + " of type " + content + " returned...");
				}
			}
		} catch (FileNotFoundException fnfe) {
			try {
				fileNotFound(output, dataOutput, fileRequest);
			} catch (IOException ioe) {
				System.err.println("error with file, not found exception: " + ioe);
			}

		} catch (IOException ioe) {
			System.err.println("server error: " + ioe);
		 } finally {
			try {
				input.close();
				output.close();
				dataOutput.close();
				client.close();
			} catch (Exception e) {
				System.err.println("error closing stream: " + e.getMessage());
			}

			if (verbose) {
				System.out.println("connection closed...");
			}
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

	private String getContentType(String fileRequest) {
		if (fileRequest.endsWith(".htm") || fileRequest.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}

	private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(webRoot, fileNotFound);
		int fileLength = (int) file.length();
		String content = "text/html";
		byte[] fileData = readFileData(file, fileLength);

		out.println("http/1.1 404 File Not Found");
		out.println("server: java http server from laura-jmr");
		out.println("date: " + new Date());
		out.println("content-type: " + content);
		out.println("content-length: " + fileLength);
		out.println();
		out.flush();

		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();

		if (verbose) {
			System.out.println("file " + fileRequested + " not found");
		}
	}*/
}