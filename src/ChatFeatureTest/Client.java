package ChatFeatureTest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Application {
    private TextArea dataTextArea;
    private TextArea chatTextArea;
    private TextField messageTextField;

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
    	chatTextArea = new TextArea();
        chatTextArea.setEditable(false);

        messageTextField = new TextField();
        messageTextField.setPromptText("Type your message...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        // Create layout
        VBox chatPane = new VBox(10, chatTextArea);
        chatPane.setPadding(new Insets(10));

        HBox messagePane = new HBox(10, messageTextField, sendButton);
        messagePane.setPadding(new Insets(10));
        HBox.setHgrow(messageTextField, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setCenter(chatPane);
        root.setBottom(messagePane);

        // Create scene
        Scene scene = new Scene(root, 400, 300);

        // Set stage properties
        primaryStage.setTitle("Client Messenger");
        primaryStage.setScene(scene);
        primaryStage.show();


        // Run the client in a background task
        Thread clientThread = new Thread(this::runClient);
        clientThread.setDaemon(true);
        clientThread.start();
    }

    private void runClient() {
        try {
            Socket clientSocket = new Socket("localhost", 4999);
            Platform.runLater(() -> {
                chatTextArea.appendText("Client connected to server\n");
            });
            InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String message;
            while ((message = bf.readLine()) != null) {
            	final String receivedMessage = message; 
                Platform.runLater(() -> {
                    dataTextArea.appendText(receivedMessage + "\n");
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (!message.isEmpty()) {
            chatTextArea.appendText("You: " + message + "\n");
            messageTextField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
