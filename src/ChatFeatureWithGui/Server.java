package ChatFeatureWithGui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Shape shape;
    private boolean running;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.shape = new Shape(); // Create a new Shape object with desired x and y values
		this.shape.setX(10);
		this.running = true;
		this.shape.setY(20);
		
	}

	public void startServer() {
		try {

			// while server is not closed, wait for a client to connect
			while (!serverSocket.isClosed()) {

				Socket socket = serverSocket.accept();
				if(socket != null) {
					System.out.println("A new client has connected!");
					ClientHandler clientHandler = new ClientHandler(socket);
					
					Thread thread = new Thread(clientHandler);
					thread.start();
//					startSendingUpdates(socket);
					
				}
				

			}

		} catch (IOException e) {

		}
	}
	
//	 public void startSendingUpdates(Socket socket) {
//		 Thread sendingThread = new Thread(() -> {
//	        try {
//	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//
//	            while (running) {
//	                // Send the serialized Shape object to the client
////	                shape.setX(shape.getX() + 1);
////	                shape.setY(shape.getY() + 1);
////	                System.out.println(shape.x + " " + shape.y);
////	                objectOutputStream.writeObject(shape);
////	                objectOutputStream.flush();
//
//	                // Delay between updates (adjust as needed)
//	                Thread.sleep(1000);
//	            }
//	        } catch (IOException | InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    });
//		sendingThread.start();
//    }
//	 
	

	public void closeServerSocket() {
		try {

			if (serverSocket != null) {
				serverSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234);
		Server server = new Server(serverSocket);
		server.startServer();

	}
}

