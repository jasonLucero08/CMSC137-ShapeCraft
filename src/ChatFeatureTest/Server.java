package ChatFeatureTest;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(4999);
		Socket server = serverSocket.accept();
		
		System.out.println("Client connected");
				
	}
}
