package ChatFeatureWithGui;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


import javax.swing.JOptionPane;

//import ChatFeature.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClientStart {
	
	private Shape receivedShape;
	
	Image playBackground = new Image("file:images/2.png", 1360, 960, true, true);
	
	ImageView imageView = new ImageView(playBackground);
//
	BackgroundImage background = new BackgroundImage(
			playBackground,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    private String windowTitle;
    public Button sendButton = new Button("Send");
    public TextArea inputArea = new TextArea();
    public TextArea chatArea = new TextArea();
    public Stage stage = new Stage();

    public ClientStart(String windowTitle) {
        this.windowTitle = windowTitle;
    }
    
    public class Client {
    	private boolean running;
    	private Socket socket;
    	private BufferedReader bufferedReader;
    	private BufferedWriter bufferedWriter;
    	private String username;

	    public Client(Socket socket, String username) {
	    	running = true;
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
						startReceivingUpdates();
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
	            String message = inputArea.getText();
	            try {
		            bufferedWriter.write(username + ": " + message);
					bufferedWriter.newLine();
					bufferedWriter.flush();
		            chatArea.appendText("You: " + message + "\n");
		            inputArea.clear();
	            }
	            catch (IOException e) {
					closeEverything(this.socket, bufferedReader, bufferedWriter);
				}
	    	});

	    }
		
		private void receiveMessage(String Text) {
	    	Platform.runLater(() -> {
	            chatArea.appendText(Text + "\n");
	    	});

	    }
		
		private void startReceivingUpdates() {
	        try {
	            ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
	            Shape receivedShape;
	            System.out.println("THIS RAN");
	            while (this.socket.isConnected()) {
	            	
	                // Receive the serialized Shape object from the server
	                receivedShape = (Shape) objectInputStream.readObject();

	                // Process the received Shape object as needed
	                System.out.println("Received Shape: x = " + receivedShape.getX() + ", y = " + receivedShape.getY());
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
    }
    
    Thread socketThread = new Thread(() -> {
    	try {
//            Scanner scanner = new Scanner(System.in);
            
//            System.out.println("Enter your username for the group chat: ");
            String username = this.windowTitle;
            Socket socket = new Socket("localhost", 1000);
            Client client = new Client(socket, username);
            client.listenForMessage();
            client.sendMessage();
            
            
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during socket connection or communication
        }
        
    });
    
    public void show() {
    	 	
    	
	    stage.setTitle(windowTitle);
	    stage.setResizable(false);
        inputArea.setPrefHeight(40);
        inputArea.setPrefWidth(195);
        chatArea.setPrefWidth(270);
        chatArea.setPrefHeight(120);
        sendButton.setPrefWidth(60);
        sendButton.setPrefHeight(40);
        
        sendButton.setLayoutX(1030);
        sendButton.setLayoutY(900);

        chatArea.setLayoutX(1030);
        chatArea.setLayoutY(770);

        inputArea.setLayoutX(1105);
        inputArea.setLayoutY(900);

//        VBox chatBox = new VBox(4, chatArea, inputArea, sendButton);
//        chatBox.setPrefSize(400, 300);
        Pane pane = new Pane(sendButton, chatArea, inputArea);
        BorderPane root = new BorderPane(pane);
        root.setBackground(new Background(background));

      

        Scene scene = new Scene(root, 1360, 960);

        stage.setScene(scene);
        stage.show(); 
    	// When you run both code segments at the same time, it is likely that the 
        // UI becomes unresponsive because the JavaFX window is running on the main application thread, 
        // while the socket communication is also running on the same thread. 
        // This can cause blocking and unresponsive behavior.
        
        socketThread.start();
        
        
    	
    }

    
}
