package ChatFeature;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatUI {
    private BorderPane root;
    private VBox chatBox;
    private TextArea chatArea;
    private TextField messageField;
    private Button sendButton;

    public ChatUI() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Chat Application");

        // Create the main layout
        root = new BorderPane();

        // Create the chat box
        chatBox = new VBox();
        chatBox.setPadding(new Insets(10));
        chatBox.setSpacing(10);

        // Create the chat area
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setPrefHeight(400);
        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);

        // Create the message input field
        messageField = new TextField();
        messageField.setPromptText("Type your message...");

        // Create the send button
        sendButton = new Button("Send");
        sendButton.setDefaultButton(true);
        sendButton.setOnAction(event -> sendMessage());

        // Add components to the chat box
        chatBox.getChildren().addAll(scrollPane, messageField, sendButton);

        // Set the chat box to the center of the main layout
        root.setCenter(chatBox);

        // Set the scene
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            chatArea.appendText("You: " + message + "\n");
            messageField.clear();
        }
    }
}