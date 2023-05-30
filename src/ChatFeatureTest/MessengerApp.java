package ChatFeatureTest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;


public class MessengerApp extends Application {
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
        primaryStage.setTitle("Server Messenger");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run the server in a background task
        Task<Void> serverTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                runServer();
                return null;
            }
        };

        Thread serverThread = new Thread(serverTask);
        serverThread.setDaemon(true);
        serverThread.start();

        System.out.println("Server running");
    }

    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (!message.isEmpty()) {
            chatTextArea.appendText("You: " + message + "\n");
            messageTextField.clear();
        }
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(4999);
            while (true) {
                Socket server = serverSocket.accept();
                Platform.runLater(() -> {
                    chatTextArea.appendText("Client connected\n");
                });
                System.out.println("Client connected");
                InputStreamReader in = new InputStreamReader(server.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                
                String str = bf.readLine();
                Platform.runLater(() -> {
                    chatTextArea.appendText(str + "\n");
                });
                System.out.println("client: "+ str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}