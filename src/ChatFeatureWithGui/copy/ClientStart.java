package ChatFeatureWithGui.copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

//import ChatFeature.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientStart {

    private String windowTitle;
    public Button sendButton = new Button("Send");
    public TextArea inputArea = new TextArea();
    public TextArea chatArea = new TextArea();
    public Stage stage = new Stage();

    public ClientStart(String windowTitle) {
        this.windowTitle = windowTitle;
    }
    
    public class Client {
    	
    	private Socket socket;
    	private BufferedReader bufferedReader;
    	private BufferedWriter bufferedWriter;
    	private String username;
    
	    public Client(Socket socket, String username) {
			try {
				this.socket = socket;
				this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.username = username;
			} catch (IOException e) {
//				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}
	    
	    public void sendMessage() {
			try {
				bufferedWriter.write(username);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				Scanner scanner = new Scanner(System.in);
				while (socket.isConnected()) {
					
					sendButton.setOnAction(event -> sendMessage(inputArea, chatArea));
					
				}
			} catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
			}
		}

		public void listenForMessage() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String msgFromGroupChat;
					while (socket.isConnected()) {
						try {
							msgFromGroupChat = bufferedReader.readLine();
//							System.out.println(msgFromGroupChat);
							receiveMessage(msgFromGroupChat);
						} catch (IOException e) {
							closeEverything(socket, bufferedReader, bufferedWriter);
						}
					}
				}
			}).start();
		}
		
		public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
			try {
				if (bufferedReader != null){
					bufferedReader.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void sendMessage(TextArea inputArea, TextArea chatArea) {
	    	Platform.runLater(() -> {
	        	System.out.println("Green");
	            String message = inputArea.getText();
	            try {
		            bufferedWriter.write(username + ": " + message);
					bufferedWriter.newLine();
					bufferedWriter.flush();
		            chatArea.appendText("You: " + message + "\n");
		            inputArea.clear();
	            }
	            catch (IOException e) {
					closeEverything(socket, bufferedReader, bufferedWriter);
				}
	    	});

	    }
		
		private void receiveMessage(String Text) {
	    	Platform.runLater(() -> {
	            chatArea.appendText(Text + "\n");
	    	});

	    }
    }
    
    public void show() {
    	
    	
        stage.setTitle(windowTitle);
        chatArea.setEditable(false);
        inputArea.setPrefHeight(50);
//        TextArea chatArea = new TextArea();
        

//        TextArea inputArea = new TextArea();
        
        VBox chatBox = new VBox(10, chatArea, inputArea, sendButton);
        BorderPane root = new BorderPane(chatBox);

        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.show();
        
    	// When you run both code segments at the same time, it is likely that the 
        // UI becomes unresponsive because the JavaFX window is running on the main application thread, 
        // while the socket communication is also running on the same thread. 
        // This can cause blocking and unresponsive behavior.
        
        Thread socketThread = new Thread(() -> {
        	try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter your username for the group chat: ");
                String username = scanner.nextLine();
                Socket socket = new Socket("localhost", 1234);
                Client client = new Client(socket, username);
                client.listenForMessage();
                client.sendMessage();
                
            } catch (IOException e) {
                e.printStackTrace();
                // Handle any exceptions that occur during socket connection or communication
            }
            
        });
        socketThread.start();
    	
    }

    
}
